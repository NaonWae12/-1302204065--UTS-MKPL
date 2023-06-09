/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package uts;

import java.time.LocalDate;
import java.time.Month;
import java.util.LinkedList;
import java.util.List;

/**
 *
 * @author Saeful anwar
 */

public class Employee {
    private String employeeId;
    private String firstName;
    private String lastName;
    private String idNumber;
    private String address;

    private int yearJoined;
    private int monthJoined;
    private int dayJoined;
    private int monthWorkingInYear;

    private boolean isForeigner;
    private boolean gender; // true = Laki-laki, false = Perempuan

    private int monthlySalary;
    private int otherMonthlyIncome;
    private int annualDeductible;

    private String spouseName;
    private String spouseIdNumber;

    private List<String> childNames;
    private List<String> childIdNumbers;

    public Employee(String employeeId, String firstName, String lastName, String idNumber, String address,
            int yearJoined, int monthJoined, int dayJoined, boolean isForeigner, boolean gender) {
        this.employeeId = employeeId;
        this.firstName = firstName;
        this.lastName = lastName;
        this.idNumber = idNumber;
        this.address = address;
        this.yearJoined = yearJoined;
        this.monthJoined = monthJoined;
        this.dayJoined = dayJoined;
        this.isForeigner = isForeigner;
        this.gender = gender;

        childNames = new LinkedList<String>();
        childIdNumbers = new LinkedList<String>();
    }

    public class Address {
        private String adress;
        private String city;

        private String zipCode;

        public Address(String adress, String city, String zipCode) {
            this.adress = adress;
            this.city = city;
            ;
            this.zipCode = zipCode;
        }

        // Setter methods
        public void setStreet(String adress) {
            this.adress = adress;
        }

        public void setCity(String city) {
            this.city = city;
        }

        public void setZipCode(String zipCode) {
            this.zipCode = zipCode;
        }

        // Getter methods
        public String getStreet() {
            return adress;
        }

        public String getCity() {
            return city;
        }

        public String getZipCode() {
            return zipCode;
        }
    }

    /**
     * Fungsi untuk menentukan gaji bulanan pegawai berdasarkan grade kepegawaiannya
     * (grade 1: 3.000.000 per bulan, grade 2: 5.000.000 per bulan, grade 3:
     * 7.000.000 per bulan)
     * Jika pegawai adalah warga negara asing gaji bulanan diperbesar sebanyak 50%
     */

    // method setMonthlySalary menggunakan pendekatan polimorfisme
    public interface Grade {
        int getMonthlySalary();
    }

    public class Grade1 implements Grade {
        private final boolean isForeigner;

        public Grade1(boolean isForeigner) {
            this.isForeigner = isForeigner;
        }

        @Override
        public int getMonthlySalary() {
            return isForeigner ? 4500000 : 3000000;
        }
    }

    public class Grade2 implements Grade {
        private final boolean isForeigner;

        public Grade2(boolean isForeigner) {
            this.isForeigner = isForeigner;
        }

        @Override
        public int getMonthlySalary() {
            return isForeigner ? 7500000 : 5000000;
        }
    }

    public class Grade3 implements Grade {
        private final boolean isForeigner;

        public Grade3(boolean isForeigner) {
            this.isForeigner = isForeigner;
        }

        @Override
        public int getMonthlySalary() {
            return isForeigner ? 10500000 : 7000000;
        }
    }

    public void setMonthlySalary(int grade) {
        Grade gradeImpl;
        switch (grade) {
            case 1:
                gradeImpl = new Grade1(isForeigner);
                break;
            case 2:
                gradeImpl = new Grade2(isForeigner);
                break;
            case 3:
                gradeImpl = new Grade3(isForeigner);
                break;
            default:
                throw new IllegalArgumentException("Grade not recognized");
        }
        monthlySalary = gradeImpl.getMonthlySalary();
    }
    // polimorpism

    public void setAnnualDeductible(int deductible) {
        this.annualDeductible = deductible;
    }

    public void setAdditionalIncome(int income) {
        this.otherMonthlyIncome = income;
    }

    public void setSpouse(String spouseName, String spouseIdNumber) {
        this.spouseName = spouseName;
        this.spouseIdNumber = idNumber;
    }

    public void addChild(String childName, String childIdNumber) {
        childNames.add(childName);
        childIdNumbers.add(childIdNumber);
    }

    public int getAnnualIncomeTax() {
        // Menghitung berapa lama pegawai bekerja dalam setahun ini, jika pegawai sudah
        // bekerja dari tahun sebelumnya maka otomatis dianggap 12 bulan.
        LocalDate date = LocalDate.now();

        if (date.getYear() == yearJoined) {
            monthWorkingInYear = date.getMonthValue() - monthJoined;
        } else {
            monthWorkingInYear = 12;
        }

        return TaxFunction.calculateTax(monthlySalary, otherMonthlyIncome, monthWorkingInYear, annualDeductible,
                spouseIdNumber.equals(""), childIdNumbers.size());
    }

}
