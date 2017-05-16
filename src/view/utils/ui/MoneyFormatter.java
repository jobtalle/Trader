package view.utils.ui;

public final class MoneyFormatter {
    private static final String PREFIX = "€ ";

    public static String intToMoney(final long money)
    {
        final String moneyString = Long.toString(money);
        StringBuilder sb = new StringBuilder(moneyString);

        int pointCounter = 5;
        for(int i = moneyString.length(); i-- > 0;) {
            if(i == moneyString.length() - 2) {
                sb.insert(i, ',');

                if (i == 0)
                    sb.insert(0, '0');
            }

            if(--pointCounter == 0 && i > 0) {
                pointCounter = 3;

                sb.insert(i, '.');
            }
        }

        return PREFIX + sb.toString();
    }
}
