package com.eliseev.app.services;

import com.eliseev.app.models.Map;
import com.eliseev.app.models.OrderUser;
import com.eliseev.app.models.OrderUserProduct;
import com.eliseev.app.models.Product;
import com.eliseev.app.models.Transport;
import com.eliseev.app.repository.custom.OrderDAO;
import com.eliseev.app.services.dto.OrderDTO;
import com.eliseev.app.services.dto.ProductDTO;
import com.eliseev.app.services.dto.RouteDTO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class OrderService extends AbstractService<OrderUser, OrderDAO> {

    private TransportService transportService;

    @Autowired
    public OrderService(OrderDAO dao,
                        TransportService transportService) {
        super(dao);
        this.transportService = transportService;
    }

    private Logger logger = LoggerFactory.getLogger(PointService.class);


    public void getCost(String[] transports, List<ProductDTO> productDTOs) {

        List<Transport> transportsObj =  Arrays.stream(transports)
                .map(transportService::getTransportByName)
                .collect(Collectors.toList());

    }

    @Transactional
    public OrderDTO createOrder(OrderDTO orderDTO) {

        List<ProductDTO> productDTOs = orderDTO.getProductDTOs();
        java.util.Map<Transport, Long> transports = transportService.getTransports(
                orderDTO.getRouteDTO().getRoute().getId(),
                orderDTO.getRouteDTO().getStartMapSerialNumber(),
                orderDTO.getRouteDTO().getEndMapSerialNumber());

        getOrderCostAndCoefficient(orderDTO, transports, productDTOs);
        OrderUser orderUser = mapOrderDTO(orderDTO);
        logger.info("{}", orderUser);
        OrderUser saved = super.dao.save(orderUser);
        logger.info("{}", saved);
        return orderDTO;
    }

    public OrderUser mapOrderDTO(OrderDTO orderDTO) {
        OrderUser orderUser = new OrderUser();
        orderUser.setFirstName(orderDTO.getFirstName());
        orderUser.setSecondName(orderDTO.getSecondName());
        orderUser.setEmail(orderDTO.getEmail());
        orderUser.setCost(orderDTO.getCost());
        orderUser.setCoefficient(orderDTO.getCoefficient());

        List<ProductDTO> productDTOS = orderDTO.getProductDTOs();
        List<OrderUserProduct> orderUserProducts = new ArrayList<>();
        for (ProductDTO productDTO : productDTOS) {

            OrderUserProduct orderUserProduct = new OrderUserProduct();
            orderUserProduct.setOrderUser(orderUser);
            Product product = productDTO.getProduct();
            orderUserProduct.setWeight(productDTO.getWeight());
            orderUserProduct.setProduct(product);
            orderUserProducts.add(orderUserProduct);
        }
        orderUser.setOrderUserProducts(orderUserProducts);

        RouteDTO routeDTO = orderDTO.getRouteDTO();

        Map startMap = new Map();
        startMap.setId(routeDTO.getStartMapId());
        orderUser.setStartMap(startMap);

        Map endMap = new Map();
        endMap.setId(routeDTO.getEndMapId());
        orderUser.setEndMap(endMap);

        orderUser.setRoute(routeDTO.getRoute());

        return orderUser;
        /*orderUser.setOrderUserProducts();*/
    }

    private OrderDTO getOrderCostAndCoefficient(OrderDTO orderDTO, java.util.Map<Transport, Long> transportsDistance, List<ProductDTO> productDTOs) {

        int[] weights = new int[productDTOs.size()];
        int[] amounts = new int[productDTOs.size()];
        double generalSum = 0, generalProductWeight = 0, coefficient;
        int transportAmount, i = 0, generalTransportWeight = 0;

        for (ProductDTO productDTO : productDTOs) {
            weights[i++] = productDTO.getProduct().getBoxWeight();
            generalProductWeight += productDTO.getWeight();
        }

        for (java.util.Map.Entry<Transport, Long> transportLongEntry : transportsDistance.entrySet()) {
            i = 0;
            for (ProductDTO productDTO : productDTOs) {
                amounts[i] = (int) Math.ceil(productDTO.getWeight() / productDTO.getProduct().getBoxWeight());
                i++;
            }

            transportAmount = getTransportAmount(transportLongEntry.getKey().getMaxWeight(), productDTOs.size(), weights, amounts);
            generalSum += transportAmount * transportLongEntry.getKey().getCostPerKm() * transportLongEntry.getValue();
            generalTransportWeight += transportLongEntry.getKey().getMaxWeight();
        }

        coefficient = (generalProductWeight / generalTransportWeight) * 100;
        orderDTO.setCoefficient(coefficient);
        orderDTO.setCost(generalSum);
        logger.info("{}", orderDTO);
        return orderDTO;
    }

    private int getTransportAmount(int W, int n, int[] gold, int[] generalAmount) {

        System.out.println("W = " + W);
        System.out.println("n = " + n);
        System.out.print("gold: ");
        for (int g : gold) {
            System.out.print(g + " ");
        }
        System.out.print("\ngeneralAmount: ");
        for (int g : generalAmount) {
            System.out.print(g + " ");
        }
        System.out.println();
        int amount = 0;
        int[][] amountTmp = new int[n + 1][W + 1];
        int[][] d = new int[n + 1][W + 1];
        int[] appear = new int[n];
        int tmp;
        int max;
        int index;



        while (true) {

            for (int i = 0; i < n + 1; i++) {
                for (int j = 0; j < W + 1; j++) {
                    d[i][j] = 0;
                    amountTmp[i][j] = 0;
                }
            }

            for (int i = 1; i < n + 1; i++) {
                for (int j = 1; j < W + 1; j++) {
                    if (j >= gold[i - 1]) {
                        index = j - gold[i - 1];

                        if (amountTmp[i][index] + 1 <= generalAmount[i - 1] && (tmp = d[i][index] + gold[i - 1]) <= W && tmp > d[i - 1][j]) {
                            amountTmp[i][j] = amountTmp[i][index] + 1;
                            d[i][j] = tmp;
                            max = tmp;
                        } else {
                            amountTmp[i][j] = 0;
                            d[i][j] = d[i - 1][j];
                            max = d[i][j];
                        }

                        if (generalAmount[i - 1] > 0 && (tmp = d[i - 1][index] + gold[i - 1]) <= W && tmp > max) {
                            amountTmp[i][j] = 1;
                            d[i][j] = tmp;
                        }
                    } else {
                        amountTmp[i][j] = 0;
                        d[i][j] = d[i - 1][j];
                    }

                }
            }


            max = d[n][0];
            index = 0;
            for (int i = 0; i < W + 1; i++) {
                if (max < d[n][i]) {
                    max = d[n][i];
                    index = i;
                }
            }

            int tmpIndex;

            System.out.println();
            for (int i = n; i > 0; i--) {
                if (amountTmp[i][index] == 0 && d[i - 1][index] == d[i][index]) {
                    appear[i - 1] = 0;
                } else {
                    tmpIndex = index - gold[i - 1];
                    if (amountTmp[i][index] - 1 == amountTmp[i][tmpIndex] && d[i][index] - gold[i - 1] == d[i][tmpIndex]) {
                        appear[i - 1] = amountTmp[i][index];
                    } else {
                        appear[i - 1] = amountTmp[i][index];
                    }
                    index = tmpIndex;
                }
            }


            System.out.print("   ");
            for (int i = 0; i < W + 1; i++) {
                System.out.printf("%3d.", i);
            }
            System.out.println();
            for (int i = 0; i < n + 1; i++) {
                System.out.print(i + ". ");
                for (int j = 0; j < W + 1; j++) {
                    System.out.printf("%4d", amountTmp[i][j]);
                }
                System.out.println();
            }

            for (int e : appear) {
                System.out.print(e + " ");
            }
            System.out.println();

            System.out.print("   ");
            for (int i = 0; i < W + 1; i++) {
                System.out.printf("%3d.", i);
            }
            System.out.println();
            for (int i = 0; i < n + 1; i++) {
                System.out.print(i + ". ");
                for (int j = 0; j < W + 1; j++) {
                    System.out.printf("%4d", d[i][j]);
                }
                System.out.println();
            }

            System.out.println();
            System.out.println("---------------------------------------------------------------------");

            if (max != 0) {

                for (int i = 0; i < n; i++) {
                    generalAmount[i] -= appear[i];
                }
                amount++;

            } else break;
        }
        System.out.println(amount);

        return amount;
    }


}
