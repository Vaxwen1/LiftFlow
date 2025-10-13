import java.sql.Timestamp;

public class Donations {

    private int donationId;
    private double donationAmount;
    private Timestamp donationDate;
    private int userId;
    private int jarId;

    public Donations() {}

    public Donations(int donationId, double donationAmount, Timestamp donationDate, int userId, int jarId) {
        this.donationId = donationId;
        this.donationAmount = donationAmount;
        this.donationDate = donationDate;
        this.userId = userId;
        this.jarId = jarId;
    }

    public int getDonationId() {
        return donationId;
    }

    public void setDonationId(int donationId) {
        this.donationId = donationId;
    }

    public double getDonationAmount() {
        return donationAmount;
    }

    public void setDonationAmount(double donationAmount) {
        this.donationAmount = donationAmount;
    }

    public Timestamp getDonationDate() {
        return donationDate;
    }

    public void setDonationDate(Timestamp donationDate) {
        this.donationDate = donationDate;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public int getJarId() {
        return jarId;
    }

    public void setJarId(int jarId) {
        this.jarId = jarId;
    }

    @Override
    public String toString() {
        return "Donation{" +
                "donationId=" + donationId +
                ", donationAmount=" + donationAmount +
                ", donationDate=" + donationDate +
                ", userId=" + userId +
                ", jarId=" + jarId +
                '}';
    }
}
