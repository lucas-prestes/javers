package org.javers.core.json;

import org.joda.time.LocalDateTime;

import java.math.BigDecimal;
import java.util.Map;

public class CdoSnapshotSerialized {
    private Object commitPK;

    //commitMetadata
    private Map<String, String> commitProperties;
    private String commitAuthor;
    private LocalDateTime commitDate;
    private BigDecimal commitId;

    //snapshot
    private long version;
    private String snapshotState; //JSON
    private String changedProperties; //JSON
    private String snapshotType;

    //globalId
    private String globalIdFragment;
    private String globalIdLocalId;
    private String globalIdTypeName;
    private String ownerGlobalIdFragment;
    private String ownerGlobalIdLocalId;
    private String ownerGlobalIdTypeName;

    public CdoSnapshotSerialized withCommitPK(Object commitPK) {
        this.commitPK = commitPK;
        return this;
    }

    public CdoSnapshotSerialized withCommitProperties(Map<String, String> commitProperties) {
        this.commitProperties = commitProperties;
        return this;
    }

    public CdoSnapshotSerialized withCommitAuthor(String commitAuthor) {
        this.commitAuthor = commitAuthor;
        return this;
    }

    public CdoSnapshotSerialized withCommitDate(LocalDateTime commitDate) {
        this.commitDate = commitDate;
        return this;
    }

    public CdoSnapshotSerialized withCommitId(BigDecimal commitId) {
        this.commitId = commitId;
        return this;
    }

    public CdoSnapshotSerialized withVersion(long version) {
        this.version = version;
        return this;
    }

    public CdoSnapshotSerialized withSnapshotState(String snapshotState) {
        this.snapshotState = snapshotState;
        return this;
    }

    public CdoSnapshotSerialized withChangedProperties(String changedProperties) {
        this.changedProperties = changedProperties;
        return this;
    }

    public CdoSnapshotSerialized withSnapshotType(String snapshotType) {
        this.snapshotType = snapshotType;
        return this;
    }

    public CdoSnapshotSerialized withGlobalIdFragment(String globalIdFragment) {
        this.globalIdFragment = globalIdFragment;
        return this;
    }

    public CdoSnapshotSerialized withGlobalIdLocalId(String globalIdLocalId) {
        this.globalIdLocalId = globalIdLocalId;
        return this;
    }

    public CdoSnapshotSerialized withGlobalIdTypeName(String globalIdTypeName) {
        this.globalIdTypeName = globalIdTypeName;
        return this;
    }

    public CdoSnapshotSerialized withOwnerGlobalIdFragment(String ownerGlobalIdFragment) {
        this.ownerGlobalIdFragment = ownerGlobalIdFragment;
        return this;
    }

    public CdoSnapshotSerialized withOwnerGlobalIdLocalId(String ownerGlobalIdLocalId) {
        this.ownerGlobalIdLocalId = ownerGlobalIdLocalId;
        return this;
    }

    public CdoSnapshotSerialized withOwnerGlobalIdTypeName(String ownerGlobalIdTypeName) {
        this.ownerGlobalIdTypeName = ownerGlobalIdTypeName;
        return this;
    }

    public Object getCommitPK() {
        return commitPK;
    }

    public Map<String, String> getCommitProperties() {
        return commitProperties;
    }

    public String getCommitAuthor() {
        return commitAuthor;
    }

    public LocalDateTime getCommitDate() {
        return commitDate;
    }

    public BigDecimal getCommitId() {
        return commitId;
    }

    public long getVersion() {
        return version;
    }

    public String getSnapshotState() {
        return snapshotState;
    }

    public String getChangedProperties() {
        return changedProperties;
    }

    public String getSnapshotType() {
        return snapshotType;
    }

    public String getGlobalIdFragment() {
        return globalIdFragment;
    }

    public String getGlobalIdLocalId() {
        return globalIdLocalId;
    }

    public String getGlobalIdTypeName() {
        return globalIdTypeName;
    }

    public String getOwnerGlobalIdFragment() {
        return ownerGlobalIdFragment;
    }

    public String getOwnerGlobalIdLocalId() {
        return ownerGlobalIdLocalId;
    }

    public String getOwnerGlobalIdTypeName() {
        return ownerGlobalIdTypeName;
    }
}

