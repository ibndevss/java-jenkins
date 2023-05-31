package myor.matrix.master.tenant;

import org.springframework.stereotype.Component;

@Component
public class TenantContext {

	private static ThreadLocal<String> tenantId = new InheritableThreadLocal<>();
	private static ThreadLocal<String> tokenAuth = new InheritableThreadLocal<>();

    public static String getTenantId() {
        return tenantId.get();
    }

    public static void setTenantId(String tenant) {
    	tenantId.set(tenant);
    }
    
    public static String getTokenAuth() {
        return tokenAuth.get();
    }

    public static void setTokenAuth(String token) {
    	tokenAuth.set(token);
    }

    public static void clear() {
    	tenantId.set(null);
    	tokenAuth.set(null);
    }
}
