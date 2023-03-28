package com.example.microgrowth.Service.Classe;

import com.example.microgrowth.DAO.Entities.Training;
import com.example.microgrowth.DAO.Entities.TrainingSortCriteria;
import com.example.microgrowth.DAO.Entities.TrainingSpecs;
import com.example.microgrowth.DAO.Entities.User;
import com.example.microgrowth.DAO.Repositories.TrainingRepository;
import com.example.microgrowth.Service.Interfaces.ITrainingService;

import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import javax.mail.MessagingException;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.Date;
import java.util.List;
import java.util.Comparator;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;


@AllArgsConstructor
@Service
public class TrainingService implements ITrainingService {
    @Autowired
    private EmailSenderService senderService;
    //@Autowired
    private TrainingRepository trainingRepository;


    @Override
    public Training add(Training training) {

        return trainingRepository.save(training);
    }

    @Override
    public Training edit(Training training) {

        return trainingRepository.save(training);
    }

    @Override
    public List<Training> selectAll() {

        return trainingRepository.findAll();
    }

    @Override
    public Training selectById(int idtraining) {

        return trainingRepository.findById(idtraining);
    }

    @Override
    public void deleteById(int idtraining) {
           trainingRepository.deleteById(idtraining);
    }

    @Override
    public void delete(Training training) {
        trainingRepository.delete(training);

    }

    @Override
    public List<Training> addAll(List<Training> listT) {

        return trainingRepository.saveAll(listT);
    }

    @Override
    public void deleteAll(List<Training> listT) {

    }

    @Override
    public void deleteByDate() {
        List<Training> listT = trainingRepository.selectByDate();
        trainingRepository.deleteAll(listT);

    }

    @Override
    public void sendMailExpiration() throws MessagingException {
        //List<User> listU = trainingRepository.selectUsers();

        for(User us : trainingRepository.selectUsers()) {
            senderService.sendEmail(us.getEmail(), " Evenement PréExpiré", "Evenement Expiré", "C:/Users/HP/Documents/mir.pdf");
        }
    }


    @Override
    public List<Training> selectByDate() {
        return trainingRepository.selectByDate1();
    }

    @Override
    public List<Training> findwithsorting(String field,String type) {
        return trainingRepository.findAll(Sort.by(Sort.Direction.fromString(type) ,field));
    }

    public List<Training> sortEvents(List<Training> events, TrainingSortCriteria sortCriteria, boolean ascending) {
        Comparator<Training> comparator = getComparator(sortCriteria, ascending);
        events.sort(comparator);
        return events;
    }

    private Comparator<Training> getComparator(TrainingSortCriteria sortCriteria, boolean ascending) {
        Comparator<Training> comparator = null;
        switch (sortCriteria) {
            case PRICE:
                comparator = Comparator.comparing(Training::getPrice);
                break;
            case START_DATE:
                comparator = Comparator.comparing(Training::getStartDate);
                break;
            case NBROFPLACE:
                comparator = Comparator.comparingInt(Training::getNbrOfPlace);
                break;
            default:
                throw new IllegalArgumentException("Invalid sort criteria: " + sortCriteria);
        }
        if (!ascending) {
            comparator = comparator.reversed();
        }
        return comparator;
    }

    public Stream<Training> searchTraining(String title, float price, String subject) {
        Specification<Training> spec = Specification.where(null);

        if (title != null && !title.isEmpty()) {
            spec = spec.and(TrainingSpecs.titleContains(title));
        }

        if (price != 0.0) {
            spec = spec.and(TrainingSpecs.priceLessThanOrEqual(price));
        }

        if (subject != null && !subject.isEmpty()) {
            spec = spec.and(TrainingSpecs.subjectContains(subject));
        }

        return trainingRepository.findAll(spec).stream();
    }
    public Stream<Training> searchTraining1(String title) {
        Specification<Training> spec = Specification.where(null);

        if (title != null && !title.isEmpty()) {
            spec = spec.and(TrainingSpecs.titleContains(title));
        }

        return trainingRepository.findAll(spec).stream();
    }
    public Stream<Training> searchTraining2( String subject) {
        Specification<Training> spec = Specification.where(null);



        if (subject != null && !subject.isEmpty()) {
            spec = spec.and(TrainingSpecs.subjectContains(subject));
        }

        return trainingRepository.findAll(spec).stream();
    }
    public Stream<Training> searchTraining3( float price) {
        Specification<Training> spec = Specification.where(null);



        if (price != 0.0) {
            spec = spec.and(TrainingSpecs.priceLessThanOrEqual(price));
        }



        return trainingRepository.findAll(spec).stream();
    }
}
