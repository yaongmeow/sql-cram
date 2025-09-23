select i.item_id, i.item_name
from item_info i, item_tree t
where t.parent_item_id is null and t.item_id = i.item_id
order by i.item_id;