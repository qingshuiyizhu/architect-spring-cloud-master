package org.cloud.mircoservice.realm;


import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.springframework.beans.factory.annotation.Autowired;

public class MyShiroRealm extends AuthorizingRealm{

	@Autowired
	// private UserService userService;
	 
	@Override
	protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
		String userName=(String)principals.getPrimaryPrincipal();
		SimpleAuthorizationInfo authorizationInfo=new SimpleAuthorizationInfo();
		System.out.println("-----------------------doGetAuthorizationInfo");
		//authorizationInfo.setStringPermissions(userService.getPermissions(userName)); 
		return authorizationInfo;
	}


	@Override
	protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {
		System.out.println("-----------------------doGetAuthenticationInfo");   
		String userName=(String)token.getPrincipal();
	// 	User user=userService.getByName(userName);
	 	/*
			if(user!=null){
				AuthenticationInfo authcInfo=new SimpleAuthenticationInfo(user.getName(),user.getPassword(),getName());
				return authcInfo;
			}else{
				return null;				
			} */
		 return null;	
	}
	
}
