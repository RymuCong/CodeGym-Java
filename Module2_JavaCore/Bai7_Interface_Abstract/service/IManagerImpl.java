package service;

import entity.Staff;

public class IManagerImpl implements IManager{
    private Staff[] staffList;
    private int staffLimit;

    public IManagerImpl(Staff[] staffList, int staffLimit) {
        this.staffList = staffList;
        this.staffLimit = staffLimit;
    }

    public Staff[] getStaffList() {
        return staffList;
    }

    public void setStaffList(Staff[] staffList) {
        this.staffList = staffList;
    }

    public int getStaffLimit() {
        return staffLimit;
    }

    public void setStaffLimit(int staffLimit) {
        this.staffLimit = staffLimit;
    }

    @Override
    public void displayStaff() {

    }

    @Override
    public void addStaff() {

    }

    @Override
    public void updateStaff() {

    }

    @Override
    public void deleteStaff() {

    }
}
