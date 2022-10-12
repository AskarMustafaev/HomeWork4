import java.util.Random;

public class Main {
    public static int bossHealth = 800;
    public static int bossDamage = 50;
    public static String bossDefence;
    public static int[] heroesHealth = {300, 120, 200, 250, 130};
    public static int[] heroesDamage = {15, 0, 20, 35, 25};
    public static String[] heroesName = {"Golem", "Medic", "Berserk", "Thor", "Lucky"};
    public static int roundNumber = 0;
    static Random random = new Random();

    public static void main(String[] args) {
        printStatistics();
        while (!isGameFinished()) {
            playRound();
        }
    }

    public static void playRound() {
        roundNumber++;
        thorEffect();
        berserkBlocking();
        medicHeall();
        luckyEvade();
        golemSkill();
        setBossDefence();
        setBossDamage();
        setHeroesDamage();
        printStatistics();
    }
    private static void thorEffect(){
        boolean stun = random.nextBoolean();
        if (heroesHealth[3] > 0 && stun){
            bossDamage = 0;
            System.out.println("Thor Effect is successfully completed");
        }
    }
    private static void berserkBlocking(){
        int block = random.nextInt(15);
        if (heroesHealth[2] > 0){
            heroesDamage[2] += block;
            heroesHealth[2] += block;
        }
    }
    private static void luckyEvade(){
        boolean evade = random.nextBoolean();
        if (evade){
            heroesHealth[4] += bossDamage;
            System.out.println("Boss is missed");
        }
    }
    private static void golemSkill(){
        bossDamage -= bossDamage /5;
    }
    private static void medicHeall(){
        int randomHealing = random.nextInt(30);
        if (heroesHealth[1] >= 0){
            for (int i = 0; i < heroesName.length; i++) {
                if (heroesHealth[i]!= heroesHealth[1] && heroesHealth[i] <= 100){
                    heroesHealth[i]+= randomHealing;
                    System.out.println("Medic healing: " + heroesName[i] + "" + randomHealing);
                    break;
                }

            }
        }
    }


    public static void printStatistics() {
        System.out.println("ROUND " + roundNumber + " --Fight--");
        System.out.println("Boss health: " + bossHealth + "; damage: "
                + bossDamage + "; defence: " + (bossDefence == null ? "No defence" : bossDefence));
        System.out.println("------------------------");
        for (int i = 0; i < heroesHealth.length; i++) {
            System.out.println(heroesName[i] + " health: " + heroesHealth[i] + " damage: " + heroesDamage[i]);
        }
    }
    public static void setBossDefence(){
        Random random = new Random();
        int randomIndex = random.nextInt(heroesName.length);
        bossDefence = heroesName[randomIndex];
    }

    public static boolean isGameFinished() {
        if (bossHealth <= 0) {
            System.out.println("Heroes won!!! The end!");
            return true;
        }
        boolean allHeroesDead = true;
        for (int i = 0; i < heroesHealth.length; i++) {
            if (heroesHealth[i] > 0) {
                allHeroesDead = false;
                break;
            }
        }
        if (allHeroesDead){
            System.out.println("Boss won!!! Try agan!");
        }
        return allHeroesDead;
    }


    public static void setBossDamage() {
        for (int i = 0; i < heroesHealth.length; i++) {
            if (heroesHealth[i] > 0) {
                if(heroesHealth[i] - bossDamage < 0){
                    heroesHealth[i] = 0;
                } else {
                heroesHealth[i] = heroesHealth[i] - bossDamage;
                }
            }
        }
    }

    public static void setHeroesDamage() {
        for (int i = 0; i < heroesDamage.length; i++) {
            if (heroesHealth[i] > 0 && bossHealth > 0) {
                if (bossHealth - heroesDamage[i] < 0){
                    bossHealth = 0;
                } else {
                bossHealth = bossHealth - heroesDamage[i];
                }
            }
        }
    }
}
