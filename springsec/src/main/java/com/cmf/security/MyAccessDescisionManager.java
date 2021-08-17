package com.cmf.security;

import org.springframework.security.access.AccessDecisionManager;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.access.ConfigAttribute;
import org.springframework.security.authentication.InsufficientAuthenticationException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.web.FilterInvocation;

import javax.servlet.http.HttpServletRequest;
import java.util.Collection;
import java.util.Iterator;

public class MyAccessDescisionManager  implements AccessDecisionManager {
    @Override
    public void decide(Authentication authentication, Object object, Collection<ConfigAttribute> configAttributes) throws AccessDeniedException, InsufficientAuthenticationException {

            FilterInvocation fi = (FilterInvocation) object;
            HttpServletRequest request = fi.getRequest();
            if(configAttributes==null) return;
            Iterator<ConfigAttribute> it = configAttributes.iterator();
            while(it.hasNext()){
                String needRole = it.next().getAttribute();
                for(GrantedAuthority ga:authentication.getAuthorities()){
                    if(needRole.equals(ga.getAuthority())){
                        return;
                    }
                }
            }
           // MyFilterSecurityInterceptor.goToUrl(obj, "/access.jsp");
            throw new AccessDeniedException("--------MyAccessDescisionManager：decide-------权限认证失败！");
        }

    @Override
    public boolean supports(ConfigAttribute attribute) {
        return false;
    }

    @Override
    public boolean supports(Class<?> clazz) {
        return false;
    }
}
