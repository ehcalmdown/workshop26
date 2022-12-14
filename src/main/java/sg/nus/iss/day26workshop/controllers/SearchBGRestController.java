package sg.nus.iss.day26workshop.controllers;

import java.util.List;

import org.joda.time.DateTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import jakarta.json.Json;
import jakarta.json.JsonArray;
import jakarta.json.JsonArrayBuilder;
import jakarta.json.JsonObject;
import jakarta.json.JsonObjectBuilder;
import sg.nus.iss.day26workshop.models.Comment;
import sg.nus.iss.day26workshop.models.Game;
import sg.nus.iss.day26workshop.models.Games;
import sg.nus.iss.day26workshop.services.SearchBgService;

@RestController
@RequestMapping(path = "/api/bgg")
public class SearchBGRestController {

    @Autowired
    private SearchBgService bgSvc;


    @GetMapping(path = "/games")
    public ResponseEntity<String> getAllGames(@RequestParam Integer limit, @RequestParam Integer offset){
        List<Game> results = bgSvc.searchAllGames(limit, offset);
        JsonObject result = null;
        JsonObjectBuilder builder = Json.createObjectBuilder();
        Games gs = new Games();
        gs.setGames(results);
        gs.setLimit(limit);
        gs.setOffset(offset);
        gs.setTotal(results.size());
        gs.setTimestamp(DateTime.now());
        builder.add("bgg", gs.toJSON());
        result = builder.build();
        return ResponseEntity
                .status(HttpStatus.OK)
                .contentType(MediaType.APPLICATION_JSON)
                .body(null);
    }

    @GetMapping(path = "/games/rank")
    public ResponseEntity<String> getGameByRanking() {
        JsonArray result = null;
        List<Game> results = bgSvc.getGamesByRank();
        JsonArrayBuilder arrBuilder = Json.createArrayBuilder();
        for (Game g : results)
            arrBuilder.add(g.toJSON());
        result = arrBuilder.build();
        return ResponseEntity
                .status(HttpStatus.OK)
                .contentType(MediaType.APPLICATION_JSON)
                .body(result.toString());
    }

    @GetMapping(path = "/games/{gameId}")
    public ResponseEntity<String> getGameDetailsById(
            @PathVariable Integer gameId) {
        JsonObject result = null;
        System.out.println("gameId" + gameId);
        Game gameDetails = bgSvc.getGameDetailsById(gameId);
        JsonObjectBuilder objBuilder = Json.createObjectBuilder();

        objBuilder.add("game", gameDetails.toJSON());
        result = objBuilder.build();

        return ResponseEntity
                .status(HttpStatus.OK)
                .contentType(MediaType.APPLICATION_JSON)
                .body(result.toString());
    }

    @GetMapping(path = "/comment")
    public ResponseEntity<String> searchComment(@RequestParam String q, @RequestParam Integer limit, @RequestParam Integer offset){
        JsonArray results=null;
        List<Comment> cResults = bgSvc.searchCommentByKeyword(q, limit, offset);
        JsonArrayBuilder builder = Json.createArrayBuilder();
        for(Comment c : cResults)
        return ResponseEntity.status(HttpStatus.OK)
                             .contentType(MediaType.APPLICATION_JSON)
                             .body(null);
        return null;
    }

    
}

