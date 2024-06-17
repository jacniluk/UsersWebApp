package com.jacniluk.userswebapp.specifications;

import org.springframework.data.jpa.domain.Specification;

import com.jacniluk.userswebapp.model.User;

public class UserSpecifications
{
	public static Specification<User> search(String searchColumn, String searchValue)
	{
        return (root, query, criteriaBuilder) -> criteriaBuilder.equal(root.get(searchColumn), searchValue);
    }

    public static Specification<User> sort(String sortColumn, String sortType)
    {
        return (root, query, criteriaBuilder) ->
        {
            query.orderBy(sortType.equalsIgnoreCase("asc") ? criteriaBuilder.asc(root.get(sortColumn)) : criteriaBuilder.desc(root.get(sortColumn)));
            
            return null;
        };
    }
}
