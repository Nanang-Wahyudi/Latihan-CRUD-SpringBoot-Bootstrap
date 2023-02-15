package com.project.crud_spring.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "mahasiswa_tb")
public class mahasiswa {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "nama_mhs")
    private String nama;

    private String npm;
    private String prodi;
    private String foto;

    public mahasiswa() {
        super();
    }

    public mahasiswa(String nama, String npm, String prodi, String foto) {
        this.nama = nama;
        this.npm = npm;
        this.prodi = prodi;
        this.foto = foto;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getNama() {
        return nama;
    }

    public void setNama(String nama) {
        this.nama = nama;
    }

    public String getNpm() {
        return npm;
    }

    public void setNpm(String npm) {
        this.npm = npm;
    }

    public String getProdi() {
        return prodi;
    }

    public void setProdi(String prodi) {
        this.prodi = prodi;
    }

    public String getFoto() {
        return foto;
    }

    public void setFoto(String foto) {
        this.foto = foto;
    }

    @Override
    public String toString() {
        return "mahasiswa [id=" + id + ", nama=" + nama + ", npm=" + npm + ", prodi=" + prodi + ", foto=" + foto + "]";
    }

}
