package utils.ollama;

public class FailureContext {
  private final String testName;
  private final String stepDescription;
  private final Throwable error;
  private final String stackTrace;
  private final String pageSource;
  private final String screenshotPath;
  private final String elementLocator ;
  private final String elementType; // uiautomator | xpath
  private final String ussdCode;
  private final long timestamp;

  private FailureContext(Builder builder) {
    this.testName = builder.testName;
    this.stepDescription = builder.stepDescription;
    this.error = builder.error;
    this.stackTrace = builder.stackTrace;
    this.pageSource = builder.pageSource;
    this.screenshotPath = builder.screenshotPath;
    this.elementLocator = builder.elementLocator;
    this.elementType = builder.elementType;
    this.ussdCode = builder.ussdCode;
    this.timestamp = builder.timestamp;
  }

  public String getTestName() {
    return testName;
  }

  public String getStepDescription() {
    return stepDescription;
  }

  public Throwable getError() {
    return error;
  }

  public String getStackTrace() {
    return stackTrace;
  }

  public String getPageSource() {
    return pageSource;
  }

  public String getScreenshotPath() {
    return screenshotPath;
  }

  public String getElementLocator() {
    return elementLocator;
  }

  public String getElementType() {
    return elementType;
  }

  public String getUssdCode() {
    return ussdCode;
  }

  public long getTimestamp() {
    return timestamp;
  }

  public boolean isPageSourceValid() {
    if (pageSource == null || pageSource.length() < 1000) {
      System.err.println(
          "Advertencia: El Page Source capturado es nulo o demasiado corto (<1000 caracteres).");
      return false;
    }
    return true;
  }

  public static Builder builder() {
    return new Builder();
  }

  public static class Builder {
    private String testName;
    private String stepDescription;
    private Throwable error;
    private String stackTrace;
    private String pageSource;
    private String screenshotPath;
    private String elementLocator;
    private String elementType;
    private String ussdCode;
    private long timestamp = System.currentTimeMillis();

    public Builder testName(String testName) {
      this.testName = testName;
      return this;
    }

    public Builder stepDescription(String stepDescription) {
      this.stepDescription = stepDescription;
      return this;
    }

    public Builder error(Throwable error) {
      this.error = error;
      return this;
    }

    public Builder stackTrace(String stackTrace) {
      this.stackTrace = stackTrace;
      return this;
    }

    public Builder pageSource(String pageSource) {
      this.pageSource = pageSource;
      return this;
    }

    public Builder screenshotPath(String screenshotPath) {
      this.screenshotPath = screenshotPath;
      return this;
    }

    public Builder elementLocator(String elementLocator) {
      this.elementLocator = elementLocator;
      return this;
    }

    public Builder elementType(String elementType) {
      this.elementType = elementType;
      return this;
    }

    public Builder ussdCode(String ussdCode) {
      this.ussdCode = ussdCode;
      return this;
    }

    public Builder timestamp(long timestamp) {
      this.timestamp = timestamp;
      return this;
    }

    public FailureContext build() {
      return new FailureContext(this);
    }
  }
}
