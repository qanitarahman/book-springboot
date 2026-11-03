package id.ac.ub.filkom.perpustakaan.boundary;

import id.ac.ub.filkom.perpustakaan.control.RingkasanService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/")
public class BerandaController {

    private final RingkasanService ringkasanService;

    public BerandaController(RingkasanService ringkasanService) {
        this.ringkasanService = ringkasanService;
    }

    @GetMapping
    public String tampilBeranda(Model model) {
        model.addAttribute("totalStok",       ringkasanService.totalStokBuku());
        model.addAttribute("jumlahAnggota",   ringkasanService.jumlahAnggota());
        model.addAttribute("sedangDipinjam",  ringkasanService.jumlahSedangDipinjam());
        model.addAttribute("jumlahTerlambat", ringkasanService.jumlahTerlambat());
        return "beranda";
    }
}
