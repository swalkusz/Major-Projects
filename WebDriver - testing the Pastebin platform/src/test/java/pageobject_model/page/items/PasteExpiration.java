package pageobject_model.page.items;

public enum PasteExpiration {
    $NEVER,
    $BURN_AFTER_READ,
    $10_MINUTES,
    $1_HOUR,
    $1_DAY,
    $1_WEEK,
    $2_WEEKS,
    $1_MONTH,
    $6_MONTHS,
    $1_YEAR;

    public String pasteExpirationToString() {
        StringBuilder pasteExpirationString = new StringBuilder(this
                .toString()
                .substring(1)
                .replace("_"," ")
                .toLowerCase());
        for (int i = 0; i < pasteExpirationString.length(); i++) {
            if (pasteExpirationString.charAt(i) >= 'a' && pasteExpirationString.charAt(i) <= 'z') {
                pasteExpirationString.replace(i,i+1,(pasteExpirationString.charAt(i)+"").toUpperCase());
                break;
            }
        }
        return pasteExpirationString.toString();
    }

}
