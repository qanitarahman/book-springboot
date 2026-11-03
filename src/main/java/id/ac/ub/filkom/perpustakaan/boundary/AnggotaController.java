package id.ac.ub.filkom.perpustakaan.boundary;

import id.ac.ub.filkom.perpustakaan.control.KelolaAnggotaService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("/anggota")
public class AnggotaController {

    private final KelolaAnggotaService kelolaAnggotaService;

    public AnggotaController(KelolaAnggotaService kelolaAnggotaService) {
        this.kelolaAnggotaService = kelolaAnggotaService;
    }

    @GetMapping
    public String tampilHalaman(Model model) {
        model.addAttribute("daftarAnggota", kelolaAnggotaService.daftarAnggota());
        return "anggota";
    }

    @PostMapping
    public String tambahAnggota(@RequestParam String idAnggota,
                                @RequestParam String nama,
                                @RequestParam int batasPinjam,
                                RedirectAttributes ra) {
        kelolaAnggotaService.tambahAnggota(idAnggota, nama, batasPinjam);
        return "redirect:/anggota";
    }

    @PostMapping("/hapus")
    public String hapusAnggota(@RequestParam String idAnggota, RedirectAttributes ra) {
        String pesan = kelolaAnggotaService.hapusAnggota(idAnggota);
        if (pesan != null) ra.addFlashAttribute("pesan", pesan);
        return "redirect:/anggota";
    }
}
