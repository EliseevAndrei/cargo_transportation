$(document).ready(function () {

    $('#route-table').DataTable();

    let products = [];

    $.ajaxSetup({
        async: false
    });
    $.getJSON('/products/list', function (response) {

        products = response;

    });
    $.ajaxSetup({
        async: true
    });

    let optionsHtml = '';
    products.forEach(e => {
        optionsHtml += `<option value=${e.id}>${e.name}</option>`;
    });
    let trForTableProductHtml =
        '                                             <tr class="tr-product">\n' +
        '                                                <td>\n' +
        '                                                    <select name="products" id="products">\n' +
        `${optionsHtml}` +
        '                                                    </select>\n' +
        '                                                </td>\n' +
        '                                                <td>\n' +
        '                                                    <input type="number" name="weight" id="weight"/>\n' +
        '                                                </td>\n' +
        '                                            </tr>';


    $('.detail').click(function (e) {

        e.preventDefault();
        let href = $(this).attr('href');

        $.getJSON(href, function (response) {
            let html = '<tbody>';
            for (let map of response) {
                html += `<tr><td>${map.depPoint}</td>` +
                    `<td>${map.arrPoint}</td>` +
                    `<td>${map.distance}</td>` +
                    `<td>${map.transport.name}</td>` +
                    `<td>${map.transport.speed}</td>` +
                    `<td>${map.transport.maxWeight}</td>` +
                    `<td>${map.transport.costPerKm}</td></tr>`
            }
            html += '</tbody>';
            $('.routeForm #point-table').append(html);
        });
        $('#routeFormModal').modal();
    });

    $('#routeFormModal').on('hide.bs.modal', function () {
        $('#point-table tbody').remove();
    });

    $('.a-radio').click(function (e) {
        e.preventDefault();

        let productTableDOMElement = $('.myForm #product-table');
        productTableDOMElement.append(trForTableProductHtml);
        console.log(trForTableProductHtml);
        $('.myForm #orderModal').modal();
        let routeDTO = {
            route: {
                id: $(this).parent().siblings("#routeId").text(),
                company: {
                    id: $(this).parent().siblings("#companyId").text()
                }
            }
            ,
            distance: $(this).parent().siblings("#distance").text(),
            cost: $(this).parent().siblings("#cost").text(),
            startMapId: $(this).parent().siblings("#startMapId").text(),
            endMapId: $(this).parent().siblings("#endMapId").text(),
            startMapSerialNumber: $(this).parent().siblings("#startMapSerialNumber").text(),
            endMapSerialNumber: $(this).parent().siblings("#endMapSerialNumber").text()
        };
        console.log(routeDTO);

        $('.myForm  #add-product-btn').click(e => {
            e.preventDefault();
            productTableDOMElement.append(trForTableProductHtml);
        });
        let orderDTO;
        let productsWeights = [];
        let productWeight = {};
        let productId;
        let product;
        $('.myForm #order-submit-btn').click(e => {
            e.preventDefault();
            $('.myForm .tr-product').each(function () {
                productId = $(this).find('#products option:selected').val();
                console.log(productId);
                product = products.find(function (e) {
                    console.log(e);
                    return e.id == Number(productId);
                });
                console.log(product);
                productWeight = {
                    product: product,
                    weight: $(this).find('#weight').val()
                };
                productsWeights.push(productWeight);
            });
            console.log(productsWeights);

            orderDTO = {
                routeDTO: routeDTO,
                secondName: $('.myForm #surname').val(),
                firstName: $('.myForm #firstName').val(),
                email: $('.myForm #email').val(),
                productDTOs: productsWeights
            };
            console.log(orderDTO);

            $.ajax({
                url: "/orders/list/filled",
                type: 'POST',
                dataType: "json",
                contentType: "application/json",
                data: JSON.stringify(orderDTO),
                success: function (data, textStatus, xhr) {
                    alert("success");
                    $(location).attr('href', '/orders');
                },
                error: function (xhr, textStatus, errorThrown) {
                    alert("error");
                    let errorBlock = $('.myForm #errors');
                    let response = JSON.parse(xhr.responseText);
                    response.errors.forEach(function (error) {
                        errorBlock.append("<p>" + error + "</p>");
                        /*console.log(error);*/
                    });
                    errorBlock.css("display", "block");
                }
            })

        });

    });

});

