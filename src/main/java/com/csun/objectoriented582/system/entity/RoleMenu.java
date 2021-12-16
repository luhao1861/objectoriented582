package com.csun.objectoriented582.system.entity;
import javax.persistence.*;
import java.io.Serializable;
/** 
 * @team mackie Studio 
 * @Author QQ:15577969 
 * @Date 2021-12-11 02:46:31 
 */
@Entity
@Table ( name ="sys_role_menu" )
public class RoleMenu  implements Serializable {

	private static final long serialVersionUID =  2803334010731257601L;

	@Id
   	@Column(name = "id" )
    @GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

   	@Column(name = "role_id" )
	private Long roleId;

   	@Column(name = "menu_id" )
	private Long menuId;

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }


  public Long getRoleId() {
    return roleId;
  }

  public void setRoleId(Long roleId) {
    this.roleId = roleId;
  }


  public Long getMenuId() {
    return menuId;
  }

  public void setMenuId(Long menuId) {
    this.menuId = menuId;
  }

}
