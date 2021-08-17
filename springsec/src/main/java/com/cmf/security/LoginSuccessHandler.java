package com.cmf.security;

import com.alibaba.druid.util.StringUtils;
import org.springframework.core.env.SystemEnvironmentPropertySource;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.security.web.authentication.SavedRequestAwareAuthenticationSuccessHandler;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;

public class LoginSuccessHandler implements AuthenticationSuccessHandler {
    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws ServletException, IOException {
        UserDetails userDetails= (UserDetails) authentication.getPrincipal();
        System.out.println("用户："+userDetails.getUsername());
        String userName = request.getParameter("j_username");
        Map map = new HashMap<>();
        PrintWriter out = response.getWriter();
       /* if(ValidatorUtil.isMobile(userName)) {
            SaasUserWeb saasUserWeb = new SaasUserWeb();
            saasUserWeb.setAccount(userName);
            SaasUserWeb user = saasTenantService.selectUserLoginInfo(saasUserWeb);
            map.put("Id",user.getId());
            map.put("userId",user.getUserId());
            map.put("tentantId",user.getTenantId());
            map.put("time",user.getLastLoginTime());
        }else{
            SaasUserOauth saasUserOauth = new SaasUserOauth();
            saasUserOauth.setOauthUnionId(userName);
            List<SaasUserOauth> saasUserOauth1 = saasTenantService.selectOAuthLoginInfo(saasUserOauth);
            String newUserId = saasUserOauth1.get(0).getUserId();
            if(StringUtils.isNotBlank(newUserId)){
                SaasUserWeb saasUserWeb = new SaasUserWeb();
                saasUserWeb.setUserId(newUserId);
                SaasUserWeb newUser = saasTenantService.selectUserLoginInfo(saasUserWeb);
                if(null != newUser){
                    Date timee = newUser.getLastLoginTime();//最后登录时间
                    if(null != timee){
                        map.put("time",timee);
                    }
                    map.put("Id",newUser.getId());
                }
            }
            map.put("userId",saasUserOauth1.get(0).getUserId());
            map.put("tentantId",saasUserOauth1.get(0).getTenantId());
        }
        Collection<? extends GrantedAuthority> list = authentication.getAuthorities();
        response.setCharacterEncoding("UTF-8");
        response.setContentType("application/json");

        if( list == null || list.size() == 0 ){
            out.println("{\"code\":\"0\",\"url\":\"\"}");
            return;
        }

        if(StringUtils.isNotBlank((String) map.get("Id"))) {
            SaasUserWeb saasUserWeb1 = new SaasUserWeb();
            saasUserWeb1.setId((String) map.get("Id"));
            saasUserWeb1.setLastTimeLogin((Date) map.get("time"));
            saasUserWeb1.setLastLoginTime(new Date());
            saasTenantService.updateUserLoginInfo(saasUserWeb1);
        }
        String userId = (String)map.get("userId");
        String tokenn = CreateUUIdUtil.Uuid();
        RedisUtil.set("xxxx." + (String)map.get("tentantId") +"."+userId,tokenn);
        RedisUtil.expire("xxxx." + (String)map.get("tentantId") +"."+userId,900);
        SaasTenant saasTenant = saasTenantService.selectSaasTenantServcie((String) map.get("tentantId"));
        if(null != saasTenant){
            String tenantType = saasTenant.getTenantType();
            if(StringUtils.isNotBlank(tenantType)){
                tenantType1 = tenantType;
            }
        }

        SysUser sysUser = userService.selectUserByUserId(userId);
        String name = "";
        if(null!=sysUser){
            name = sysUser.getUserName();
        }
        Integer status = sysUser.getStatus();
        if(null != status && status == 3){
            sysLogService.createLog(BusinessType.loginOp.toString(), EventType.platform_login.toString(),name+"登录失败","", Loglevel.error.toString(),ip,source);
            out.println("{\"code\":\"2\",\"msg\":\"您已被管理员禁用！\",\"url\":\"/disablePage.jsp \"}");//disablePage.jsp
            return;
        }else if (null != status && status == 89){
            sysLogService.createLog(BusinessType.loginOp.toString(), EventType.platform_login.toString(),name+"登录失败","", Loglevel.error.toString(),ip,source);
            out.println("{\"code\":\"3\",\"msg\":\"您已在当前企业离职！\",\"userId\":\"" + userId + "\",\"url\":\"/quitPage.jsp \"}");//quitPage.jsp

        }else {
            if(null != status && status == 2){
                SysUser sysUserr = new SysUser();
                sysUserr.setId(sysUser.getId());
                sysUserr.setStatus(1);
                userService.updateUserByUserId(sysUserr);
            }*/
            //sysLogService.createLog(BusinessType.loginOp.toString(), EventType.platform_login.toString(), name + "登录成功", "", Loglevel.info.toString(), ip, source);
            //获取当前的sessionid值
            String jsessionId = request.getSession().getId();
            out.println("{\"code\":\"1\",\"msg\":\"查询成功！\"}");
        }
    }

