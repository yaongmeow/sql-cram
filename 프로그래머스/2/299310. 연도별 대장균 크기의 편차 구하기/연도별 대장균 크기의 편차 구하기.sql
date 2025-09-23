# select
#     date_format(differentiation_date, '%Y') as year, 
#     size_of_colony - tmp.prev_colony as year_dev,
#     ecoli_data.id
# from ecoli_data, 
#     (select distinct b.id, ifnull(b.size_of_colony, a.size_of_colony) as prev_colony 
#     from ecoli_data a, ecoli_data b
#      where a.id = b.parent_id or b.parent_id is null) tmp
# where tmp.id = ecoli_data.id
# order by year asc, year_dev asc;

select cast(t.year as unsigned) as YEAR, t.size_of_colony - e.size_of_colony as YEAR_DEV, e.id as ID
from (
    select 
        tmp.year,
        max(tmp.size_of_colony) as size_of_colony
    from (
            select
                date_format(differentiation_date, '%Y') as year,
                size_of_colony
                from ecoli_data
        ) tmp
    group by tmp.year
) t, ecoli_data e
where t.year = date_format(e.differentiation_date, '%Y')
order by cast(t.year as unsigned) asc, t.size_of_colony - e.size_of_colony asc;