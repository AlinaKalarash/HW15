package com.example.HW15;

import com.example.HW15.service.NoteService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

//TODO:
// POST /note/delete - видалити нотатку по ID. Після видалення нотатки відбувається редирект на /note/list
// GET /note/edit?id=xxx - сторінка редагування нотатку (відкривається по натисненню на кнопку Редагувати на списку нотаток).
// POST /note/edit - сюди відправляється запит на редагування нотатки. Після збереження оновленого контенту нотатки відбувається редирект на /note/list
//GET /note/list - отримати список нотаток. Виводиться список нотаток (title та content), кожну нотатку можна видалити або редагувати

@Controller
@RequestMapping("/note")
public class NoteController {
    NoteService service = new NoteService();

    @GetMapping("/list")
    public void list() {
        service.listAll();
    }

    @PostMapping("/delete")
    public ModelAndView delete() {
        service.deleteById(1);
        return new ModelAndView("test");
    }

    @GetMapping("/edit")
    public ModelAndView editById(@RequestParam(name = "id") long id) {
        edit(id);
        return new ModelAndView().addObject("test");
    }

    @PostMapping("/edit")
    public ModelAndView edit(long id) {
        service.update(service.getById(id));
        return new ModelAndView("test");
    }
}
