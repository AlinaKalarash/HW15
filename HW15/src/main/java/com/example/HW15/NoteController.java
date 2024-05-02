package com.example.HW15;

import com.example.HW15.entity.Note;
import com.example.HW15.service.NoteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.servlet.ModelAndView;

import java.util.Random;

//TODO:
//GET /note/edit?id=xxx - сторінка редагування нотатку (відкривається по натисненню на кнопку Редагувати на списку нотаток).
// POST /note/edit - сюди відправляється запит на редагування нотатки. Після збереження оновленого контенту нотатки відбувається редирект на /note/listAllNotes
//GET /note/listAllNotes - отримати список нотаток. Виводиться список нотаток (title та content), кожну нотатку можна видалити або редагувати
// POST /note/delete - видалити нотатку по ID. Після видалення нотатки відбувається редирект на /note/listAllNotes

@Controller
@RequestMapping("/note")
public class NoteController {
    @Autowired
    NoteService service = new NoteService();

    @PostMapping("/create")
    public ModelAndView createNote(
            @RequestParam(value = "title") String title,
            @RequestParam(value = "content") String content) {
        Note note = new Note();
        note.setId(new Random().nextInt());
        note.setTitle(title);
        note.setContent(content);
        service.add(note);
        return listAllNotes();
    }

    @GetMapping("/list")
    public ModelAndView listAllNotes() {
        ModelAndView resul = new ModelAndView("note/listAll");
        resul.addObject("notes", service.listAll());
        return resul;
    }

    @PostMapping("/delete")
    public ModelAndView deleteNote(
            @RequestParam(value = "id") long id) throws HttpClientErrorException.NotFound {
        service.deleteById(id);
        return listAllNotes();
    }

    @GetMapping("/edit")
    public ModelAndView editById(@RequestParam(name = "id") long id) throws HttpClientErrorException.NotFound {
        return new ModelAndView("note/updatesNotes").addObject("note", service.getById(id));
    }

    @PostMapping("/edit")
    public ModelAndView edit(
            @RequestParam(value = "id") long id,
            @RequestParam(value = "title") String title,
            @RequestParam(value = "content") String content) {
        Note note = new Note();
        note.setId(id);
        note.setTitle(title);
        note.setContent(content);
        service.update(note);
        return listAllNotes();
    }
}
