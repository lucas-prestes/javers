package org.javers.core.diff;

import org.javers.common.patterns.visitors.Visitable;
import org.javers.core.diff.changetype.NewObject;
import org.joda.time.LocalDateTime;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

import static org.javers.common.validation.Validate.*;

/**
 * <h2>Main entity in Javers model</h2>
 *
 * Diff is a set of (atomic) changes between two graphs of objects.
 * <br/><br/>
 *
 * Typically it is used to capture and trace changes made by user on his domain data.
 * In this case diff is done between previous and current state of a bunch of domain objects.
 * <br/><br/>
 *
 * Diff holds following data:
 * <ul>
 *     <li>who did change the data - {@link #getAuthor()}</li>
 *     <li>when the change was made - {@link #getDiffDate()}</li>
 *     <li>list of atomic changes between two graphs</li>
 * </ul>
 *
 * Diff is similar notion to <i>commit</i> in GIT or <i>revision</i> in SVN.
 * <br/><br/>
 *
 * <h2>Hints for DiffRepository implementation</h2>
 * <ul>
 *    <li/>In Domain-driven design language, Diff is an <i>aggregate</i>, while Change is a <i>Value Object</i>.
 *    <li/>After persisting in database, Diff is considered immutable so it can not be updated.
 *    <li/>Since Diff is an <i>aggregate</i> it can be neatly persisted in document database like MongoDB.
 *    <li/>Persisting Diff in any kind of database is easy. Javers provides flexible
 *         JSON serialization/deserialization engine,
 *         designed as abstraction layer between Java types and specific database types.
 *    <li/>Essentially, object-oriented data are persisted as JSON.
 * </ul>
 *
 * @see org.javers.core.json.JsonConverter
 *
 * @author bartosz walacik
 */
public class Diff implements Visitable<ChangeVisitor>{
    private long id;
    private final List<Change> changes;
    private final String author;
    private final LocalDateTime diffDate;

    public Diff(String author) {
        argumentIsNotNull(author, "author should not be null");

        this.author = author;
        this.diffDate = new LocalDateTime();
        this.changes = new ArrayList<>();
    }

    /**
     * Unique revision identifier, assigned by {@link org.javers.repository.api.DiffRepository}
     * @see  #assignId(long)
     */
    public long getId() {
        return id;
    }

    /**
     * sets Diff.id, should be called by {@link org.javers.repository.api.DiffRepository}
     * before persisting
     *
     * @throws IllegalArgumentException if given id <= 0
     * @throws IllegalAccessException if revision is not new
     */
    void assignId(long id) {
        argumentCheck(id > 0, "id should be positive long");

        conditionFulfilled(isNew(),"id already assigned");

        this.id = id;
    }

    /**
     * identifier of user who authored the change,
     * typically login or numeric id
     */
    public String getAuthor() {
        return author;
    }

    /**
     * date when change was made by user
     */
    public LocalDateTime getDiffDate() {
        return diffDate;
    }

    /**
     * @return unmodifiable list
     */
    public List<Change> getChanges() {
        return Collections.unmodifiableList(changes);
    }

    /**
     * Returns true if diff hasn't been persisted yet and has no id assigned.
     * State <i>new</i> is similar to <i>transient</i> in Hibernate
     * <br/>
     *
     * Returns false if diff was persisted
     *
     * @return id == 0
     * @see #getId()
     */
    private boolean isNew() {
        return id == 0;
    }

    public void addChange(Change change, Object affectedCdo) {
        addChange(change);
        change.setAffectedCdo(affectedCdo);
    }

    private void addChange(Change change) {
        changes.add(change);
    }

    public boolean hasChanges() {
        return !changes.isEmpty();
    }

    public void addChanges(Collection<Change> changeSet) {
        for (Change change : changeSet) {
            addChange(change);
        }
    }

    @Override
    public void accept(ChangeVisitor changeVisitor) {
        for(Change change : changes) {
            change.accept(changeVisitor);
        }
    }

    private int count(Class<? extends Change> changeType){
        int c = 0;
        for(Change change : changes) {
            if (change.getClass() == changeType) {
                c++;
            }
        }
        return c;
    }
}