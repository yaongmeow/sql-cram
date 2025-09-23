select id, tmp.fish_name, tmp.length 
from (select n.fish_name, max(i.length) as length
    from fish_info i, fish_name_info n
    where i.fish_type = n.fish_type
    group by n.fish_name) tmp,
    fish_info i, fish_name_info n
where tmp.fish_name = n.fish_name and n.fish_type = i.fish_type and tmp.length = i.length order by id asc;