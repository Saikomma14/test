import java.util.*;

public class RewardPointCalculation {
    public static void main(String[] args) {

        //create one user with three months of transaction

        List<Long> transaction_jan_month_user1 = Arrays.asList(
                Long.valueOf(200), Long.valueOf(80), Long.valueOf(40),
                Long.valueOf(100));
        List<Long> transaction_feb_month_user1 = Arrays.asList(
                Long.valueOf(200), Long.valueOf(80), Long.valueOf(40),
                Long.valueOf(100));
        List<Long> transaction_mar_month_user1 = Arrays.asList(
                Long.valueOf(200), Long.valueOf(80), Long.valueOf(40),
                Long.valueOf(100));

        Map<String, List<Long>> map_user = new HashMap<>();
        map_user.put("Jan", transaction_jan_month_user1);
        map_user.put("Feb", transaction_feb_month_user1);
        map_user.put("Mar", transaction_mar_month_user1);

        Map<String, Map<String, List<Long>>> transactionOfUsers = new HashMap<>();
        transactionOfUsers.put("user1", map_user);

        //method call for each user's total reward point calculation for all given three months
        Map<String, Long> result = execute(transactionOfUsers);

    }

    //method to get all user's total reward point calculation for all given three months
    private static Map<String, Long> execute(
            Map<String, Map<String, List<Long>>> transactionOfUsers) {
        final Map<String, Long> map = new HashMap<>();
        transactionOfUsers.forEach((user, transactionsOfMonths) -> {
            map.put(user, calculateRewardForEachUser(transactionsOfMonths));
        });
        return map;
    }

    //Three months reward point addition for each user
    private static long calculateRewardForEachUser(
            Map<String, List<Long>> transactionsOfMonths) {
        long totalRewardForAllMonths = transactionsOfMonths.entrySet().stream()
                .mapToLong((e) -> {
                    return calculationTotalRewardPoint(e.getValue());
                }).sum();
        return totalRewardForAllMonths;
    }

    //reward point calculation for each transaction
    private static long calculateRewardPoints(final long transactionAmt) {
        if (transactionAmt >= 50 && transactionAmt <= 100) {
            return transactionAmt - 50;
        } else if (transactionAmt > 100) {
            return (transactionAmt - 100) * 2 + 50;
        } else {
            return 0;
        }
    }

    //Total reward point addition of each month's transactions  
    private static long calculationTotalRewardPoint(
            final List<Long> collection_rewardPoints_eachTransaction) {
        return collection_rewardPoints_eachTransaction.stream()
                .mapToLong((transactionAmt) -> {
                    return calculateRewardPoints(transactionAmt);
                }).sum();
    }
}