package custom_methods;

/**
 * Exception thrown when an element cannot be found on the page.
 * Provides detailed information about the locator and locator type that failed.
 */
public class ElementNotFoundException extends RuntimeException {
    
	private static final long serialVersionUID = 1L;
    private String locator;
    private String locatorType;
    
    /**
     * Constructor with message only.
     * 
     * @param message - the error message.
     */
    public ElementNotFoundException(String message) {
        super(message);
    }
    
    /**
     * Constructor with message and cause.
     * 
     * @param message - the error message.
     * @param cause - the underlying exception that caused this error.
     */
    public ElementNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }
    
    /**
     * Constructor with detailed locator information.
     * 
     * @param message - the error message.
     * @param locator - the locator value that failed.
     * @param locatorType - the type of locator (id, xpath, etc.).
     */
    public ElementNotFoundException(String message, String locator, String locatorType) {
        super(message);
        this.locator = locator;
        this.locatorType = locatorType;
    }
    
    /**
     * Constructor with detailed locator information and cause.
     * 
     * @param message - the error message.
     * @param locator - the locator value that failed.
     * @param locatorType - the type of locator (id, xpath, etc.).
     * @param cause - the underlying exception that caused this error.
     */
    public ElementNotFoundException(String message, String locator, String locatorType, Throwable cause) {
        super(message, cause);
        this.locator = locator;
        this.locatorType = locatorType;
    }
    
    @Override
    public String getMessage() {
        StringBuilder sb = new StringBuilder(super.getMessage());
        if (locator != null && locatorType != null) {
            sb.append(" [Locator: '").append(locator).append("', Type: '").append(locatorType).append("']");
        }
        return sb.toString();
    }
    
    public String getLocator() {
        return locator;
    }
    
    public String getLocatorType() {
        return locatorType;
    }
}