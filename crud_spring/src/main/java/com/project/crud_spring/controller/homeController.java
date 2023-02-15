package com.project.crud_spring.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.project.crud_spring.entity.mahasiswa;
import com.project.crud_spring.repository.mahasiswaRepository;

import jakarta.servlet.http.HttpSession;

@Controller
public class homeController {

    @Autowired
    private mahasiswaRepository mahasiswaRepo;

    // Halaman Home
    @GetMapping("/")
    public String home() {
        return "index";
    }

    // Pagination & Sorting
    @GetMapping("page/{pageNo}")
    public String findPaginateAndSorting(@PathVariable(value = "pageNo") int pageNo, // Punya Pagination
            @RequestParam("sortField") String sortField, // Punya Sorting
            @RequestParam("sortDir") String sortDir, // Punya Sorting
            Model m) {

        // Sorting
        Sort sort = sortDir.equalsIgnoreCase(Sort.Direction.ASC.name()) ? Sort.by(sortField).ascending()
                : Sort.by(sortField).descending();

        // Pagination
        Pageable pageable = PageRequest.of(pageNo, 3, sort);
        Page<mahasiswa> page = mahasiswaRepo.findAll(pageable);
        List<mahasiswa> list = page.getContent();

        m.addAttribute("pageNo", pageNo);
        m.addAttribute("totalElements", page.getTotalElements());
        m.addAttribute("totalPage", page.getTotalPages());
        m.addAttribute("all_mahasiswa", list);

        // Lanjutan Sorting
        m.addAttribute("sortField", sortField);
        m.addAttribute("sortDir", sortDir);
        m.addAttribute("revSortDir", sort.equals("asc") ? "desc" : "asc");

        return "contents/data";
    }

    // Halaman Data Mahasiswa
    @GetMapping("/data_mhs")
    public String data(Model m) {

        // Elemen Sebelum Masuk Pagination
        // List<mahasiswa> list = mahasiswaRepo.findAll();
        // m.addAttribute("all_mahasiswa", list);

        // Penambahan Variabel sortField & sortDir Setelah Di Pagination Dan Sorting
        return findPaginateAndSorting(0, "id", "desc", m);
    }

    // Halaman Input Data
    @GetMapping("/inputData")
    public String inputData() {
        return "contents/inputData";
    }

    // Halaman Edit Data
    @GetMapping("/edit_data/{id}")
    public String editData(@PathVariable(value = "id") Long id, Model m) {

        Optional<mahasiswa> mhswa = mahasiswaRepo.findById(id);
        mahasiswa mhs = mhswa.get();
        m.addAttribute("mhswa", mhs);

        return "contents/edit";
    }

    // Save Data Jika Berhasil, Bagian Pesan Masih Error
    @PostMapping("/save_data")
    public String saveData(@ModelAttribute mahasiswa mhs, HttpSession session) {

        mahasiswaRepo.save(mhs);
        session.setAttribute("msg", "Product Added Sucessfully..");

        return "redirect:/data_mhs";
    }

    // Update Data Jika Berhasil, Bagian Pesan Masih Error
    @PostMapping("/update_data")
    public String updateData(@ModelAttribute mahasiswa mhs, HttpSession session) {

        mahasiswaRepo.save(mhs);
        session.setAttribute("msg", "Product Update Sucessfully..");

        return "redirect:/data_mhs";
    }

    // Hapus Data Jika Berhasil, Bagian Pesan Masih Error
    @GetMapping("/delete_data/{id}")
    public String deleteData(@PathVariable(value = "id") long id, HttpSession session) {

        mahasiswaRepo.deleteById(id);
        session.setAttribute("msg", "Product Delete Sucessfully..");

        return "redirect:/data_mhs";
    }

}
