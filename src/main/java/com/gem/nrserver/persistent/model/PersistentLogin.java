package com.gem.nrserver.persistent.model;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.Date;

/**
 * Created by kimtung on 2/17/16.
 */
@Entity
@Table(
        name = "persistent_logins",
        uniqueConstraints = @UniqueConstraint(columnNames = {"username", "device_id"}))
public class PersistentLogin {

    @Column(name = "username", nullable = false)
    private String username;

    @Column(name = "device_id", nullable = false)
    private String deviceId;

    @Id
    @GenericGenerator(name="persistent_login_id_seq", strategy = "uuid2")
    @GeneratedValue(generator = "persistent_login_id_seq")
    @Column(name = "series", nullable = false, unique = true)
    private String series;

    @Column(name = "token", nullable = false, unique = true)
    private String token;

    @Column(name = "last_used")
    private Date lastUsed;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getSeries() {
        return series;
    }

    public void setSeries(String series) {
        this.series = series;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public Date getLastUsed() {
        return lastUsed;
    }

    public void setLastUsed(Date lastUsed) {
        this.lastUsed = lastUsed;
    }

    public String getDeviceId() {
        return deviceId;
    }

    public void setDeviceId(String deviceId) {
        this.deviceId = deviceId;
    }
}
