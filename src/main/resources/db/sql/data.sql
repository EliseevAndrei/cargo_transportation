insert into point (id, name) values (1, 'Брест'), (2, 'Минск'), (3, 'Гродно'), (4, 'Витебск');
insert into transport (id, name, speed, max_weight, cost_per_km) values ( 1, 'Фура', 75, 200, 12 ),( 2, 'Корабль', 75, 200 ,12),( 3, 'Поезд', 75, 200 ,12),( 4, 'Самолет', 75, 200 ,12);
insert into company (id, name) values (1, 'Елисеев биг бой');
insert into route (id, company_id) values ( 1, 1 );
insert into map (id, start_point_id, end_point_id, route_id, distance, transport_id, serial_number) values ( 1, 1, 2, 1, 100, 1, 1 ), ( 2, 2, 3, 1, 100, 1, 2 ),( 3, 3, 4, 1, 100, 1, 3 );