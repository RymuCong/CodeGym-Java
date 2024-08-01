package controller;

import model.BenhAn;
import model.BenhAnThuong;
import model.BenhAnVip;
import repository.BenhAnRepo;
import repository.BenhAnThuongRepo;
import repository.BenhAnVipRepo;

public class BenhAnController {

    BenhAnRepo benhAnRepo = new BenhAnRepo();
    BenhAnThuongRepo benhAnThuongRepo = new BenhAnThuongRepo();
    BenhAnVipRepo benhAnVipRepo = new BenhAnVipRepo();

    public void addBenhAnThuong() {
        benhAnThuongRepo.add();
    }

    public void addBenhAnVip() {
        benhAnVipRepo.add();
    }

    public void xemBenhAn() {
        benhAnRepo.findAll();
    }

    public void deleteBenhAn() {
        benhAnRepo.delete();
    }

}
