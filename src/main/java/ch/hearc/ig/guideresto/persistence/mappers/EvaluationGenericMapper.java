package ch.hearc.ig.guideresto.persistence.mappers;

import ch.hearc.ig.guideresto.business.Evaluation;

import java.util.HashSet;
import java.util.Set;

public class EvaluationGenericMapper {

    private final BasicEvaluationMapper basicEvaluationMapper;
    private final CompleteEvaluationMapper completeEvaluationMapper;

    public EvaluationGenericMapper() {
        basicEvaluationMapper = new BasicEvaluationMapper();
        completeEvaluationMapper = new CompleteEvaluationMapper();
    }

    public Set<Evaluation> getAllEvaluationsByRestaurant(int fk) {
        // get basic evaluations
        var eval = new HashSet<Evaluation>();
        eval.addAll(basicEvaluationMapper.getWhere("FK_REST", Integer.toString(fk)));
        eval.addAll(completeEvaluationMapper.getWhere("FK_REST", Integer.toString(fk)));
        return eval;
    }
}
