package travelwink.manage.dao;

import org.apache.ibatis.annotations.*;
import org.apache.ibatis.mapping.FetchType;
import org.springframework.stereotype.Repository;
import travelwink.manage.domain.entity.*;

import java.util.List;

@Mapper
@Repository
public interface DepartmentDao {

    @Insert("INSERT INTO t_department (name, create_by, create_date) VALUES (#{name}, #{createBy}, #{createDate})")
    @Options(useGeneratedKeys = true)
    int create(Department department);

    @Insert("INSERT INTO tr_department_navigation (fk_department_id, fk_navigation_id) VALUES (#{fkDepartmentId}, #{fkNavigationId})")
    @Options(useGeneratedKeys = true)
    int addNavigationRel(DepartmenuNavigationRel deptNavigationRel);

    @Insert("INSERT INTO tr_department_menu (fk_department_id, fk_menu_id) VALUES (#{fkDepartmentId}, #{fkMenuId})")
    int addMenuRel(DepartmentMenuRel deptMenuRel);

    @Select("SELECT * FROM t_department td")
    @Results({
            @Result(column = "id", property = "id"),
            @Result(column = "id", property = "menus", many = @Many(select = "travelwink.manage.dao.MenuDao.findByDeptId", fetchType = FetchType.LAZY)),
            @Result(column = "id", property = "navigations", many = @Many(select = "travelwink.manage.dao.DepartmentDao.getNavigationPermission", fetchType = FetchType.LAZY))
    })
    List<Department> findAll();

    @Select("SELECT * FROM t_department td WHERE td.status = 1")
    List<Department> findAllForSelect();

    @Select("SELECT * FROM t_department td WHERE td.id = #{deptId};")
    @Results({
            @Result(column = "id", property = "id"),
            @Result(column = "id", property = "menus", many = @Many(select = "travelwink.manage.dao.MenuDao.findByDeptId", fetchType = FetchType.EAGER))
    })
    Department findById(int deptId);

    @Select("SELECT * FROM t_department td WHERE td.id = #{deptId};")
    Department findSimpleDataById(int deptId);

    @Select("SELECT * FROM t_department td, t_menu tm, tr_department_menu rdm WHERE rdm.fk_department_id = td.id AND rdm.fk_menu_id = tm.id AND rdm.fk_department_id = #{id}")
    List<Menu> getMenuListById(String id);

    @Select("SELECT tn.* FROM t_department td, t_navigation tn, tr_department_navigation rdn WHERE rdn.fk_department_id = td.id AND  rdn.fk_navigation_id = tn.id AND rdn.fk_department_id = #{id}")
    List<Navigation> getNavigationPermission(String id);

}
