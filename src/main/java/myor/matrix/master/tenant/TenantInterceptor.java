package myor.matrix.master.tenant;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.ui.ModelMap;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.context.request.WebRequestInterceptor;

import myor.matrix.master.tenant.TenantAliasNotFoundException;
import myor.matrix.master.tenant.TenantContext;
import myor.matrix.master.tenant.TenantInterceptor;

@Component
public class TenantInterceptor implements WebRequestInterceptor {

	Logger logger = LoggerFactory.getLogger(TenantInterceptor.class);
    private static final String TENANT_HEADER = "X-Tenant";
    private static final String TOKEN_AUTH = "Authorization";
    
    @Autowired
    private TenantContext dataTenant;
    
    @Override
    public void preHandle(WebRequest request) {
        String tenantId = request.getHeader(TENANT_HEADER);
        String tokenAuth = request.getHeader(TOKEN_AUTH);

        if (tenantId != null && !tenantId.isEmpty()) {
        	
        	dataTenant.setTenantId(tenantId);
        	dataTenant.setTokenAuth(tokenAuth);
        	logger.info("Tenant header get: "+ tenantId);
        } else {
            System.out.println("Tenant header not found.");
            throw new TenantAliasNotFoundException("Tenant header not found.");
        }
    }

    @Override
    public void postHandle(WebRequest webRequest, ModelMap modelMap) {
    	dataTenant.clear();
    }

    @Override
    public void afterCompletion(WebRequest webRequest, Exception e) {

    }
}