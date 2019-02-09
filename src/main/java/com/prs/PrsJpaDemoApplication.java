package com.prs;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

import org.hibernate.dialect.identity.SybaseAnywhereIdentityColumnSupport;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.util.SystemPropertyUtils;

import com.prs.business.Product;
import com.prs.business.ProductDB;
import com.prs.business.PurchaseRequest;
import com.prs.business.PurchaseRequestDB;
import com.prs.business.PurchaseRequestLineItem;
import com.prs.business.PurchaseRequestLineItemDB;
import com.prs.business.User;
import com.prs.business.UserDB;
import com.prs.business.Vendor;
import com.prs.business.VendorDB;
import com.prs.util.Console;

@SpringBootApplication
public class PrsJpaDemoApplication {

	private static final String submittedDate = null;

	public static void main(String[] args) {
		SpringApplication.run(PrsJpaDemoApplication.class, args);

		System.out.println("Hello SpringBoot world!\n");
		
		int o = 0;
		while (0 != 9) {
			displayMenu();
			o = Console.getInt("Enter Option: ", 0, 22);
			if (o == 1) {
				// get all users
				List<User> users = UserDB.getAll();
				for (User u : users) {
					System.out.println(u);
				}
			} else if (o == 2) {
				// get user by Id
				int Id = Console.getInt("Enter user Id: ");
				User users = UserDB.getUserById(Id);
				System.out.println(users);
			}

			else if (o == 3) {
				// add user

				String userName = Console.getString("Enter user Name: ");
				String password = Console.getString("Enter password: ");
				String firstName = Console.getString("Enter first Name: ");
				String lastName = Console.getString("Enter last name number: ");
				String phoneNumber = Console.getString("enter phone number: ");
				String email = Console.getString("Enter email: ");
				boolean isReviewer = true;
				boolean isAdmin = true;

				User user = new User(0, userName, password, firstName, lastName, phoneNumber, email, isReviewer,
						isAdmin);
				UserDB.insert(user);
				System.out.println("You successfully added a user"+" "+ "for" + " " + firstName+" "+lastName);

			} else if (o == 4) {
				int Id = Console.getInt("Enter user Id to delete: ");
				User users = UserDB.getUserById(Id);
				if (users == null) {
					System.out.println("No user found for Id");
				} else {
					if (UserDB.delete(users)) {
						// success
						System.out.println("User has been successfully deleted!\n");

					} else {
						System.out.println("Error deleting user");
					}
				}
				
			}else if (o == 5) {
					// update a user
					int id = Console.getInt("Enter user id to update");
					User user = UserDB.getUserById(id);
					String name = Console.getString("Enter a new User Name: ");
					user.setUserName(name);
					UserDB.update(user);
					System.out.println("You successfully updated a user Name to " + name);
					
			} else if (o == 6) {
				List<Vendor> vendors = VendorDB.getAll();
				for (Vendor v : vendors) {
					System.out.println(v);
				}
				
			} else if (o == 7) {
				int Id = Console.getInt("Enter vendor Id: ");
				Vendor vendor = VendorDB.getVendorById(Id);
				System.out.println(vendor);
				
			} else if (o == 8) {
				String code = Console.getString("Enter vendor code: ");
				String name = Console.getString("Enter name: ");
				String address = Console.getString("Enter vendor address: ");
				String city = Console.getString("Enter vendor city: ");
				String state = Console.getString("enter state ");
				String zip = Console.getString("Enter zip: ");
				String phoneNumber = Console.getString("Enter phone number: ");
				String email = Console.getString("Enter email: ");
				boolean isPreapproved = true;

				Vendor vendor = new Vendor(0, code, name, address, city, state, zip, phoneNumber, email, isPreapproved);
				VendorDB.insert(vendor);
				System.out.println("You successfully added a Vendor");
				
			} else if (o == 9) {
				int Id = Console.getInt("Enter vendor Id: ");
				Vendor vendor = VendorDB.getVendorById(Id);
				VendorDB.delete(vendor);
				System.out.println("You successfully deleted a Vendor");
				
			} else if (o == 10) {
				// get all products
				List<Product> products = ProductDB.getAll();
				for (Product p : products) {
					System.out.println(p);
				}
			}
			     //add a new product
				else if (o==11) {
					int vendorId=Console.getInt("Enter Vendor Id");
					Vendor vendor=VendorDB.getVendorById(vendorId);
					String partNumber=Console.getString("Enter part number");
					String name=Console.getString("Enter a new product name: ");
					double price=Console.getDouble("Enter product price: ");
					String unit=Console.getString("Enter product unit: ");
					String photoPass=Console.getString("Enter photoPass: ");
					
					Product product= new Product(vendor,partNumber,name,price,unit,photoPass);
					ProductDB.insert(product);
					System.out.println("you succsessfulle added a new product");
				}
					else if(o==12) {
						int id=Console.getInt("Enter product id to delete: ");
						Product products= ProductDB.getProductById(id);
						if ( products == null) {
							System.out.println("No product found for Id");
						} else {
							if (ProductDB.delete(products)) {
								// success
								System.out.println("Product successfully deleted");
								
							} else {
								System.out.println("Error deleting a product");
							}
						}
						ProductDB.delete(products);
					}		
						else if (o == 13) {
							// get all products for a given vendor
							int vid = Console.getInt("Enter vendor id: ");
							List<Product> products = ProductDB.getAllProductsByVendorID(vid);
							for (Product p : products) {
								System.out.println(p);
							}
						}
			  else if (o == 14) {
				// list all purchase request
				List<PurchaseRequest> purchaserequest = PurchaseRequestDB.getAll();
				for (PurchaseRequest pr : purchaserequest) {
					System.out.println(pr);
				}

			  }	
			  //add a new purchase request
			  else if(o==15) {
					int userId=Console.getInt("Enter user Id: ");
					User user=UserDB.getUserById(userId);
					String description=Console.getString("Enter description: ");
					String justification=Console.getString("Enter justification: ");
					String dateNeeded = Console.getString("Date needed: ");
					String deliveryMode=Console.getString("Enter delivery mode: ");
					String status=Console.getString("Enter status: ");
					double total=Console.getDouble("Enter total: ");
					
					String reasonForRejection=Console.getString("Enter reason for rejection: ");
					
					PurchaseRequest purchaseRequest= new PurchaseRequest(user,description,justification,
							dateNeeded,deliveryMode,status,total,submittedDate, reasonForRejection);
					PurchaseRequestDB.insert(purchaseRequest);
					System.out.println("you succsessfulle added a new purchase request");
				
			} else if (o == 16) {
				// list all purchase request line Items
				List<PurchaseRequestLineItem> purchaserequestLineItem = PurchaseRequestLineItemDB.getAll();
				for (PurchaseRequestLineItem pr : purchaserequestLineItem) {
					System.out.println(pr);
				}
			}

				
			else if(o==17) {
            int Id = Console.getInt("Enter purchase request line item Id: ");
		    PurchaseRequestLineItem purchaseRequestLineItem = PurchaseRequestLineItemDB.getPurchaseRequestLineItemById(Id);
		    System.out.println(purchaseRequestLineItem);
		    
	        }
			else if(o==18) {
	
		// add purchase request line item
		int purchaseRequestId = Console.getInt("Enter purchase request id: ");
		PurchaseRequest purchaseRequest = PurchaseRequestDB.getPurchaseRequestById(purchaseRequestId);
		int productId = Console.getInt("Enter product Id: ");
		Product product = ProductDB.getProductById(productId);
		int quantity = Console.getInt("Enter product quantity: ");

		PurchaseRequestLineItem purchaseRequestLineItem1 = new PurchaseRequestLineItem(purchaseRequest, product,
				quantity);
		PurchaseRequestLineItemDB.insert(purchaseRequestLineItem1);
		System.out.println("You have successfully added new purchase request line item");
	}
			else if(o==19) {
	
		// delete purchase request line item
		int Id = Console.getInt("Enter purchase request line Item Id to delete: ");
		PurchaseRequestLineItem purchaseRequestLineItems = PurchaseRequestLineItemDB.getPurchaseRequestLineItemById(Id);
		if (purchaseRequestLineItems == null) {
			System.out.println("No purchase Request Line Items found for Id");
		} else {
			if (PurchaseRequestLineItemDB.delete(purchaseRequestLineItems)) {
				// success
				System.out.println("Purchase Request line item successfully deleted");

			} else {
				System.out.println("Error deleting purchase request line item");
			}
		}
		PurchaseRequestLineItemDB.delete(purchaseRequestLineItems);
	}

	    else if(o==20) {
	
		// update a purchase request line item
		int id = Console.getInt("Enter PRLI id to update: ");
		PurchaseRequestLineItem purchaseRequestLineItems = PurchaseRequestLineItemDB.getPurchaseRequestLineItemById(id);
		int quantity = Console.getInt("Enter a new product quantity: ");
		purchaseRequestLineItems.setQuantity(quantity);
		PurchaseRequestLineItemDB.update(purchaseRequestLineItems);
		System.out.println("You successfully updated a purchase request line item");
	}
	    else {
	
		System.out.println("Good bye!!!");
	   }
	 }

	}

	private static void displayMenu() {
		System.out.println("Options: ");
		System.out.println("1-list all users");
		System.out.println("2-Get user by Id");
		System.out.println("3-Add a user");
		System.out.println("4-Delete a user");
		System.out.println("5-Update a user");
		System.out.println();
		
		System.out.println("6-list all vendors");
		System.out.println("7-Get vendor by Id");
		System.out.println("8-Add a vendor");
		System.out.println("9-Delete a vendor");
		System.out.println();
		
		System.out.println("10-Get all products");
		System.out.println("11-Add a new product");
		System.out.println("12-Delete a product");
		System.out.println("13-get products by vendor id");
		System.out.println();
		
		System.out.println("14-List all purchase request");
		System.out.println("15-Add a new purchase request");
		System.out.println();
		
		System.out.println("16-List all purchase Request Line Items");
		System.out.println("17-Get all purchase request line items by id");
		System.out.println("18-Add a purchase request line item");
		System.out.println("19-Delete a purchase request line item");
		System.out.println("20-Update purchase request line item");
		System.out.println();
		
		System.out.println("21-exit\n");
	}
}
