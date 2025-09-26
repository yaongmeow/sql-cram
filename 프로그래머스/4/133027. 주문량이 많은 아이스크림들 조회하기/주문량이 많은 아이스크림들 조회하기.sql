# select flavor from first_half f, july j
# where 
select f.flavor as flavor from 

(select flavor, sum(total_order) as total from first_half group by flavor) f,
(select flavor, sum(total_order) as total from july group by flavor) j
where f.flavor =  j.flavor
order by f.total + j.total desc limit 3;
