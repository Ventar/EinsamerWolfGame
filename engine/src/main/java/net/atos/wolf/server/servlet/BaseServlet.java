package net.atos.wolf.server.servlet;

import jakarta.servlet.http.HttpServlet;
import lombok.Getter;
import lombok.Setter;
import net.atos.wolf.service.ServiceRegistry;

@Getter
@Setter
public class BaseServlet extends HttpServlet {
    
    protected ServiceRegistry registry;
    
    public BaseServlet(ServiceRegistry registry) {
        this.registry = registry;
    }
}
