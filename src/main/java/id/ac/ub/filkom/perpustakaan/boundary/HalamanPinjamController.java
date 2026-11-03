package id.ac.ub.filkom.perpustakaan.boundary;

import id.ac.ub.filkom.perpustakaan.control.Bukti;
import id.ac.ub.filkom.perpustakaan.control.PinjamGagalException;
import id.ac.ub.filkom.perpustakaan.control.PinjamService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("/pinjam")
public class HalamanPinjamController {

    private final PinjamService pinjamService;

    public HalamanPinjamController(PinjamService pinjamService) {
        this.pinjamService = pinjamService;
    }

    @GetMapping
    public String tampilHalaman(Model model) {
        model.addAttribute("daftarAnggota",  pinjamService.daftarAnggota());
        model.addAttribute("daftarBuku",     pinjamService.daftarBuku());
        model.addAttribute("sedangDipinjam", pinjamService.daftarSedangDipinjam());
        model.addAttribute("riwayat",        pinjamService.daftarRiwayat());
        return "pinjam";
    }

    @PostMapping
    public String pilihBuku(@RequestParam String idAnggota,
                            @RequestParam String idBuku,
                            Model model) {
        try {
            Bukti bukti = pinjamService.pinjamBuku(idAnggota, idBuku);
            return tampilBukti(bukti, model);
        } catch (PinjamGagalException e) {
            model.addAttribute("pesan", e.getMessage());
            model.addAttribute("bisaReservasi", e.isBisaReservasi());
            return tampilHalaman(model);
        }
    }

    @PostMapping("/kembali")
    public String kembalikan(@RequestParam String idTransaksi, RedirectAttributes ra) {
        try {
            pinjamService.kembalikanBuku(idTransaksi);
        } catch (PinjamGagalException e) {
            ra.addFlashAttribute("pesan", e.getMessage());
        }
        return "redirect:/pinjam";
    }

    private String tampilBukti(Bukti bukti, Model model) {
        model.addAttribute("bukti", bukti);
        return "bukti";
    }
}
