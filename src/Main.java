import java.util.Random;

public class Main {
    public static int bossHealth = 700;
    public static int bossDamage = 50;
    public static String bossDefenceType = "";
    public static int[] heroesHealth = {260, 270, 300,250};
    public static int[] heroesDamage = {20, 20, 20,0};
    public static int cure = 20;
    public static String[] heroesAttackType = {"Physical",
            "Magical", "Mental","Medical"};

    public static void main(String[] args) {
        printStatistics();
        while (!isFinished()) {
            round();
        }
    }

    public static void changeBossDefence() {
        Random r = new Random();
        int randomIndex = r.nextInt(heroesAttackType.length); //0, 1, 2
        bossDefenceType = heroesAttackType[randomIndex];
    }

    public static void round() {
        changeBossDefence();
        heroesHit();
        bossHit();
        medicalCure();
        printStatistics();


    }

    public static boolean isFinished() {
        if (bossHealth <= 0) {
            System.out.println("Heroes won!!!");
            return true;
        }
        if (heroesHealth[0] <= 0 && heroesHealth[1]
                <= 0 && heroesHealth[2] <= 0) {
            System.out.println("Boss won!!!");
            return true;
        }
        return false;
    }
    public static void medicalCure (){
        for (int i = 0; i < heroesHealth.length; i++) {
            if (heroesHealth[i] > 0 ){
                heroesHealth[i] = heroesHealth[i] + cure ;
                System.out.println(heroesAttackType[3]+" " + " cure " +cure);
            }else {
                System.out.println("УМЕРЛИ ВСЕ");
            }

        }
    }

    public static void bossHit() {
        for (int i = 0; i < heroesHealth.length; i++) {
            if (heroesHealth[i] > 0 && bossHealth > 0) {
                if (heroesHealth[i] - bossDamage < 0) {
                    heroesHealth[i] = 0;
                } else {
                    heroesHealth[i] = heroesHealth[i] - bossDamage;
                }
            }
        }
    }

    public static void heroesHit() {
        for (int i = 0; i < heroesDamage.length; i++) {
            if (heroesHealth[i] > 0 && bossHealth > 0) {
                if (bossDefenceType.equals(heroesAttackType[i])) {
                    Random r = new Random();
                    int coef = r.nextInt(10) + 2; // 0,1,2,3,4,5
                    if (bossHealth - heroesDamage[i] * coef < 0) {
                        bossHealth = 0;
                    } else {
                        bossHealth = bossHealth - heroesDamage[i] * coef;
                    }
                    System.out.println(heroesAttackType[i] +
                            " critically hit " + heroesDamage[i] * coef);
                } else {
                    if (bossHealth - heroesDamage[i] < 0) {
                        bossHealth = 0;
                    } else {
                        bossHealth = bossHealth - heroesDamage[i];
                    }
                }
            }
        }
    }

    public static void printStatistics() {
        System.out.println("_________________");
        System.out.println("Boss health: " + bossHealth);
        System.out.println("Warrior health: " + heroesHealth[0]);
        System.out.println("Magic health: " + heroesHealth[1]);
        System.out.println("Kinetic health: " + heroesHealth[2]);
        System.out.println("Medical health " + heroesHealth[3]);
        System.out.println("_________________");
    }
}
