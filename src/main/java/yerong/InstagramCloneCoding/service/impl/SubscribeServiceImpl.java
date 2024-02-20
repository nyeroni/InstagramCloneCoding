package yerong.InstagramCloneCoding.service.impl;

import com.querydsl.core.Tuple;
import com.querydsl.core.types.ExpressionUtils;
import com.querydsl.core.types.Projections;
import com.querydsl.core.types.dsl.CaseBuilder;
import com.querydsl.core.types.dsl.Expressions;
import com.querydsl.jpa.JPAExpressions;
import com.querydsl.jpa.impl.JPAQueryFactory;
import jakarta.persistence.EntityManager;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import yerong.InstagramCloneCoding.handler.exception.CustomApiException;
import yerong.InstagramCloneCoding.handler.exception.CustomValidationApiException;
import yerong.InstagramCloneCoding.repository.subs.SubscribeRepository;
import yerong.InstagramCloneCoding.service.SubscribeService;
import yerong.InstagramCloneCoding.web.dto.subscribe.SubscribeDto;

import java.util.List;

import static yerong.InstagramCloneCoding.domain.subs.QSubscribe.subscribe;
import static yerong.InstagramCloneCoding.domain.user.QUser.user;

@Service
@RequiredArgsConstructor
@Slf4j
public class SubscribeServiceImpl implements SubscribeService {

    private final SubscribeRepository subscribeRepository;

    private final JPAQueryFactory queryFactory;
    @Autowired
    private SubscribeServiceImpl(SubscribeRepository subscribeRepository, EntityManager em){
        this.subscribeRepository = subscribeRepository;
        this.queryFactory = new JPAQueryFactory(em);
    }
    @Override
    @Transactional
    public void subscribe(Long fromUserId, Long toUserId){
        if (fromUserId.equals(toUserId)) {
            throw new CustomValidationApiException("자기 자신을 구독할 수 없습니다.");
        }

        try {
            subscribeRepository.mSubscribe(fromUserId, toUserId);
        } catch (Exception e) {
            throw new CustomApiException("이미 구독을 하였습니다.");
        }
    }
    @Override
    @Transactional
    public void unSubscribe(Long fromUserId, Long toUserId){
        subscribeRepository.mUnSubscribe(fromUserId, toUserId);
    }

    @Override
    @Transactional
    public List<SubscribeDto> getSubscribeList(Long pageUserId, Long pageOwnerId){
        List<SubscribeDto> result = queryFactory
                .select(Projections.fields(SubscribeDto.class,
                        user.id,
                        user.username,
                        user.profileImageUrl,
                                ExpressionUtils.as(
                                        new CaseBuilder()
                                                .when(JPAExpressions
                                                                .selectOne()
                                                                .from(subscribe)
                                                                .where(subscribe.fromUser.id.eq(pageOwnerId)
                                                                        .and(subscribe.toUser.id.eq(user.id)))
                                                                .exists())
                                                .then(1)
                                                .otherwise(0),
                                        "subscribeState"),
                        ExpressionUtils.as(
                                new CaseBuilder()
                                        .when(user.id.eq(pageOwnerId)).then(1)
                                        .otherwise(0), "equalState")
                ))
                .from(user)
                .leftJoin(subscribe).on(subscribe.toUser.id.eq(user.id))
                .where(subscribe.fromUser.id.eq(pageUserId))
                .fetch();


        return result;
    }
}
