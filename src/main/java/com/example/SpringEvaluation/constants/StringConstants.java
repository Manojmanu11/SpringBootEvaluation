package com.example.SpringEvaluation.constants;

public class StringConstants {
    public static final String LoggedOutSuccessfully = "User Logged out Successfully : ";
    public static final String Success = "SUCCESS";
    public static final String Userregistersuccess = " Registered successfully ";
    public static final String RequestToAddUser = "Received request to add new user";
    public static final String UserLoginRequested = "Received request for user login";
    public static final String UserLogoutRequested = "Received request for user logout";
    public static final String UserLoggedInSuccessfully = "User LoggedIn Successfully.";
    public static final String SymbolsImportSuccessfully = "Symbols imported from file successfully";
    public static final String LISTSYMBOLSSUCCESS = "Symbols retrieved successfully.";
    public static final String WatchListAdded = "Successful addition of the watchlist has been confirmed   ";
    public static final String WatchlistRetrievalSuccess = "Watchlist groups retrieved successfully.";
    public static final String SymbolRetrievalSuccess = "Symbols retrieved from watchlist groups successfully.";
    public static final String ADDWATCHLISTGROUPSUCCESS = "Added Watchlist group, method: addWatchlistGroup";
    public static final String ADDSYMBOLTOWATCHLISTGROUPSSUCCESS = "Symbol added to watchlist groups, method: addSymbolToWatchListGroups";
    public static final String GETWATCHLISTGROUPSSUCCESS = "Watchlist groups retrieved, method: getWatchListGroups";
    public static final String GETSYMBOLSFROMWATCHLISTGROUPSSUCCESS = "Symbols retrieved from watchlist groups, method: getSymbolsFromWatchListGroups";
    public static final String WatchListId = "with id :";
    public static final String SymbolAdded = "Symbol is added to WatchList : ";
    public static final String WatchList = "The watchlist name is : ";
    public static final String PENDING = "PENDING";
    public static final String EXECUTED = "EXECUTED";
    public static final String PortfolioRetrievalSuccess = "Successfully retrieved user's portfolio";
    public static final String TradeHistoryRetrievalSuccess = "Successfully retrieved trade-history";
    public static final String AddOrderSuccess = "Order added successfully";
    public static final String CancelOrderSuccess = "Order cancelled successfully by ";
    public static final String ImportSymbolRequest = "Received request for importing symbols from file";
    public static final String AllSymbolsRequest = "Received request for retrieving symbols";
    public static final String DashBoard = "Returned by Dashboard service method :";
    public static final String Quote ="Returned by Quote service method :";
    public static final String UserRegistryService = "Returned by UserRegistration service method :";
    public static final String UserSession = "Returned by UserSession service method :";
    public static final String Exception = "Returning custom-exception handler method:";
    public static final String Buy = "BUY";
    public static final String Sell = "SELL";
    public static final String NoOperationPerformed = "No operation has been performed";

    public static final String InvalidEmail = "Invalid email format.Enter an email address in the proper format.";
    public static final String QuantityConstraint = "Quantity must be an integer";
    public static final String OrderOperation = "Please add correct operation";
    public static final String PasswordPattern = "^(?=.*[@#$%^&+=])(?=\\S+$).{8,}$";
    public static final String UsernameBlank = "The UserName field should not be black";
    public static final String PasswordConstraint = "The password does not match the expectations.";
    public static final String PasswordBlank = "The password field cannot be left empty.";
    public static final String UserNotFound = "User not found";
    public static final String UserNotLoggedIn = "User has not logged in";
    public static final String StockSymbolNotBlank = "Stock symbol should not be blank.";
    public static final String StockSymbolLength = "Stock symbol length should be between 10 and 25 characters.";
    public static final String OrderType = "Invalid order type. It should be 'buy' or 'sell'.";
    public static final String PricePositive = "Price must be a positive value.";
    public static final String PriceConstraint = "Price should be greater than or equal to 0.0.";
    public static final String Failure = "FAILED";
    public static final String SymbolsImportError = "Error importing symbols: ";
    public static final String GroupIdNotFound = "The specified groupId is not added by :";
    public static final String SymbolNotFound = "The mentioned symbol does not exist add correct symbol";
    public static final String SymbolAlreadyExists = "The mentioned symbol already exists in watchList .";
    public static final String GroupIdNull = "GroupId cannot be null, provide valid groupId";
    public static final String OrderNotFound = "Order not found";
    public static final String OrderInteger = "Enter integer value for orderId";
    public static final String UserAlreadyLoggedIn = "User already logged in";
    public static final String EmailAlreadyExists = "Email Id Already exists";
    public static final String UsernameAlreadyExists = "Username Already exists";


    public static final String FindByOrders = "SELECT o FROM Order o WHERE o.orderType = 'BUY'";
    public static final String FindByUserOrderIdOrderType = "SELECT o FROM Order o WHERE o.orderType = 'SELL'";
    public  static final String Saved="Successfully symbols data saved in database";
    public  static  final  String Failed="Failed while symbols saving into database";
    public static final String ValidOrderId = "Pls Enter Valid OrderId ";

}
