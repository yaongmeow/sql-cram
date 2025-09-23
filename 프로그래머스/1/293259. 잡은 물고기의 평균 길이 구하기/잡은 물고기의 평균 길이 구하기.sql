select round(avg(tmp.length), 2) as average_length
from (select ifnull(length, 10) as length from fish_info) tmp;