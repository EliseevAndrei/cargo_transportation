use targo;

set @ind1 := (select s1.id from point s1
					where s1.name = "Брест"
						limit 1);
set @ind2 := (select s2.id from point s2
							where s2.name = "Гродно"
									limit 1);



select sum(distance), group_concat(distinct name), sum(cost_per_km * distance) from (
                                                                                      select * from map,
                                                                                                    (select @max1 = ((select serial_number from map
                                                                                                                      where map.start_point_id = @ind1 and map.route_id = 1 limit 1))) as x,
                                                                                                    (select @max2 :=  (select serial_number from map
                                                                                                       where map.end_point_id = @ind2 and map.route_id = 1 limit 1)) as y
                                                                                      where serial_number between @max1 and @max2
                                                                                    ) as tab
                                                                                      inner join transport t on t.id = tab.transport_id;

---------------------------------------------------------------
use targo;




set @ind1 := (select s1.id from point s1
					where s1.name = "Брест"
						limit 1);
set @ind2 := (select s2.id from point s2
							where s2.name = "Гродно"
									limit 1);




select routeId, company_id, name as companyName
from (
       select routeId, company_id from map
                                         left join  (
         select r.id as routeId, company_id, min(serial_number), m.start_point_id, m.end_point_id
         from map m
                inner join route r on r.id = m.route_id
         where m.start_point_id =  @ind1
            or m.end_point_id = @ind2
         group by routeId
         having count(routeId) >= 2 or (m.start_point_id = @ind1 and m.end_point_id = @ind2)
       ) as tab on map.route_id = tab.routeId
       where map.start_point_id = @ind1 and routeId is not null and company_id is not null
     ) as tab1
       left join company c on c.id = company_id;














