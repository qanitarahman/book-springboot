package id.ac.ub.filkom.perpustakaan.boundary;

import id.ac.ub.filkom.perpustakaan.control.KelolaBukuService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("/buku")
public class BukuController {

    private final KelolaBukuService kelolaBukuService;

    public BukuController(KelolaBukuService kelolaBukuService) {
        this.kelolaBukuService = kelolaBukuService;
    }

    @GetMapping
    public String tampilHalaman(Model model) {
        model.addAttribute("daftarBuku", kelolaBukuService.daftarBuku());
        return "buku";
    }

    @PostMapping
    public String tambahBuku(@RequestParam String idBuku,
                             @RequestParam String judul,
                             @RequestParam int stok,
                             RedirectAttributes ra) {
        kelolaBukuService.tambahBuku(idBuku, judul, stok);
        return "redirect:/buku";
    }

    @PostMapping("/hapus")
    public String hapusBuku(@RequestParam String idBuku, RedirectAttributes ra) {
        String pesan = kelolaBukuService.hapusBuku(idBuku);
        if (pesan != null) ra.addFlashAttribute("pesan", pesan);
        return "redirect:/buku";
    }
}
