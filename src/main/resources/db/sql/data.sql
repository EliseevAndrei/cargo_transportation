insert into point (id, name) values (1, 'Брест'), (2, 'Минск'), (3, 'Гродно'), (4, 'Витебск');
insert into transport (id, name, speed, max_weight) values ( 1, 'Фура', 75, 200 ),( 2, 'Корабль', 75, 200 ),( 3, 'Поезд', 75, 200 ),( 4, 'Самолет', 75, 200 );
insert into route (id) values ( 1 );
insert into map (id, start_point_id, end_point_id, route_id, distance, transport_id, serial_number) values ( 1, 1, 2, 1, 100, 1, 1 ), ( 2, 2, 3, 1, 100, 1, 2 ),( 3, 3, 4, 1, 100, 1, 3 );