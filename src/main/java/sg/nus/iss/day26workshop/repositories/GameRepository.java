package sg.nus.iss.day26workshop.repositories;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.bson.Document;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Repository;

import sg.nus.iss.day26workshop.models.Comment;
import sg.nus.iss.day26workshop.models.Game;

@Repository
public class GameRepository {
    
    @Autowired
    private MongoTemplate mongoTemplate;

    @Autowired
    private CommentRepository commentRepo;

    public List<Game> searchAllGames(Integer limit, Integer offset){
        final Pageable pageableRequest = PageRequest.of(offset, limit);
        Query query = new Query();
        query.with(pageableRequest);

        return mongoTemplate.find(query, Document.class, "game")
                                .stream()
                                .map(d -> Game.create(d))
                                .toList();
    }

    public List<Game> getGamesByRank() {
        Query query = new Query();
        query.with(Sort.by(Sort.Direction.ASC, "ranking"));
        // lambda to find all the mongo game collections sort by ranking
        return mongoTemplate.find(query, Document.class, "game")
                        .stream()
                        .map(d -> Game.create(d))
                        .toList();
}

public Game getGameDetailsById(Integer gid) {
        Query query = new Query();
        query.addCriteria(Criteria.where("gid").is(gid));
        return mongoTemplate.findOne(query,
                        Game.class,
                        "game");
}


}
