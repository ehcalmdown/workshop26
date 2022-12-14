package sg.nus.iss.day26workshop.services;

import java.util.LinkedList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import sg.nus.iss.day26workshop.models.Comment;
import sg.nus.iss.day26workshop.models.Game;
import sg.nus.iss.day26workshop.repositories.CommentRepository;
import sg.nus.iss.day26workshop.repositories.GameRepository;

@Service
public class SearchBgService {

    @Autowired
    private GameRepository gameRepo;
    private CommentRepository commentRepo;

    public List<Game> searchAllGames(Integer limit, Integer offset) {
        return (List<Game>) gameRepo.searchAllGames(limit, offset);
    }

    public List<Game> getGamesByRank() {
        return (List<Game>) gameRepo.getGamesByRank();
    }

    public Game getGameDetailsById(Integer gameId) {
        return gameRepo.getGameDetailsById(gameId);
    }
    
    public List<Comment> searchCommentByKeyword(String s,
            Integer limit, Integer offset) {
        List<String> includes = new LinkedList<>();
        List<String> excludes = new LinkedList<>();
        for (String w : s.split(" ")) {
            System.out.println(w);
            if (w.startsWith("-")) {
                String[] exW = w.split("-");
                System.out.println("ex " + exW[1]);
                excludes.add(exW[1]);
            } else {
                System.out.println("in " + w);
                includes.add(w);
            }
        }
        return commentRepo.search(includes, excludes, limit, offset);
    }
}
