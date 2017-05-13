package press.turngeek.todos;

import org.flywaydb.core.Flyway;
import press.turngeek.db.DBConnection;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;
import javax.sql.DataSource;

@WebListener
public class FlywayListener implements ServletContextListener {
    @Override
    public void contextInitialized(ServletContextEvent servletContextEvent) {
        Flyway flyway = new Flyway();
        flyway.setDataSource(DBConnection.getDatasource());
        flyway.migrate();

        servletContextEvent.getServletContext().setAttribute("datasource", flyway.getDataSource());
    }

    @Override
    public void contextDestroyed(ServletContextEvent servletContextEvent) {
        // TODO datasource schliessen
       servletContextEvent.getServletContext().removeAttribute("datasource");
    }
}
