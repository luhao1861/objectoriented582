package com.csun.objectoriented582.system.entity;
import javax.persistence.*;
import java.io.Serializable;
/** 
 * @team mackie Studio 
 * @Author QQ:15577969 
 * @Date 2021-12-11 02:46:31 
 */
@Entity
@Table ( name ="sys_user_role" )
public class UserRole  implements Serializable {

	private static final long serialVersionUID =  8771324977520019901L;

	@Id
   	@Column(name = "id" )
    @GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

   	@Column(name = "user_id" )
	private Long userId;

   	@Column(name = "role_id" )
	private Long roleId;

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }


  public Long getUserId() {
    return userId;
  }

  public void setUserId(Long userId) {
    this.userId = userId;
  }


  public Long getRoleId() {
    return roleId;
  }

  public void setRoleId(Long roleId) {
    this.roleId = roleId;
  }

}
