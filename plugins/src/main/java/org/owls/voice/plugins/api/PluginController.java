package backend.plugins;

import org.springframework.beans.factory.serviceloader.ServiceListFactoryBean;

public class PluginController {

    public ServiceListFactoryBean serviceListFactoryBean() {
        ServiceListFactoryBean serviceListFactoryBean = new ServiceListFactoryBean();
        serviceListFactoryBean.setServiceType(PlugInInterface.class);
        return serviceListFactoryBean;
    }

}