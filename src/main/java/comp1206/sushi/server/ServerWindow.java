package comp1206.sushi.server;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import comp1206.sushi.common.*;
import comp1206.sushi.server.ServerInterface.UnableToDeleteException;

/**
 * Provides the Sushi Server user interface
 *
 */
public class ServerWindow extends JFrame implements UpdateListener {

	private static final long serialVersionUID = -4661566573959270000L;
	private ServerInterface server;
	
	/**
	 * Create a new server window
	 * @param server instance of server to interact with
	 */
	public ServerWindow(ServerInterface server) {
		super("Sushi Server");
		this.server = server;
		this.setTitle(server.getRestaurantName() + " Server");
		server.addUpdateListener(this);
		
		//Display window
		setSize(800,600);
		setLocationRelativeTo(null);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		//adding tabs
		JTabbedPane tabs = new JTabbedPane();


		tabs.setTabPlacement(JTabbedPane.LEFT);
		PostcodesTab postcodesTab = new PostcodesTab(server);
		tabs.addTab("Postcodes", postcodesTab);

		DronesTab dronesTab = new DronesTab(server);
		tabs.addTab("Drones", dronesTab);

		StaffTab staffTab = new StaffTab(server);
		tabs.addTab("Staff", staffTab);

		SuppliersTab suppliersTab = new SuppliersTab(server);
		tabs.addTab("Suppliers", suppliersTab);

		IngredientsTab ingredientsTab = new IngredientsTab(server);
		tabs.addTab("Ingredients",ingredientsTab);

		DishesTab dishesTab = new DishesTab(server);
		tabs.addTab("Dishes",	dishesTab);

		OrdersTab ordersTab = new OrdersTab(server);
		tabs.addTab("Orders", ordersTab);

		UsersTab usersTab = new UsersTab(server);
		tabs.addTab("Users", usersTab);

		//event listener for change
		tabs.addChangeListener(new ChangeListener() {
			@Override
			public void stateChanged(ChangeEvent e)
			{
				switch(e.getSource().getClass().toString())
				{
					case "class comp1206.sushi.server.PostcodesTab":
						postcodesTab.updatePostCodes();
						break;

					case "class comp1206.sushi.server.DronesTab":
						dronesTab.updateDrones();
						break;

					case "class comp1206.sushi.server.StaffTab":
						staffTab.updateStaff();
						break;

					case "class comp1206.sushi.server.SuppliersTab":
						suppliersTab.updateSuppliers();
						break;

					case "class comp1206.sushi.server.IngredientsTab":
						ingredientsTab.updateIngredients();
						break;

					case "class comp1206.sushi.server.DishesTab":
						dishesTab.updateDishes();
						break;

					case "class comp1206.sushi.server.OrdersTab":
						ordersTab.updateOrders();
						break;

					case "class comp1206.sushi.server.UsersTab":
						usersTab.updateUsers();
						break;
				}
			}
		});
		add(tabs);


		setVisible(true);
		
		//Start timed updates
		startTimer();
	}
	
	/**
	 * Start the timer which updates the user interface based on the given interval to update all panels
	 */
	public void startTimer() {
        ScheduledExecutorService scheduler = Executors.newScheduledThreadPool(1);     
        int timeInterval = 5;
        
        scheduler.scheduleAtFixedRate(() -> refreshAll(), 0, timeInterval, TimeUnit.SECONDS);
	}
	
	/**
	 * Refresh all parts of the server application based on receiving new data, calling the server afresh
	 */
	public void refreshAll() {
		
	}
	
	@Override
	/**
	 * Respond to the model being updated by refreshing all data displays
	 */
	public void updated(UpdateEvent updateEvent) {
		refreshAll();
	}
	
}
