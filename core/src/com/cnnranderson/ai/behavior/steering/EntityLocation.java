package com.cnnranderson.ai.behavior.steering;

import com.badlogic.gdx.ai.utils.Location;
import com.badlogic.gdx.math.Vector2;
import com.cnnranderson.ai.util.B2DSteeringUtils;

public class EntityLocation implements Location<Vector2> {

    Vector2 position;
    float orientation;

    public EntityLocation() {
        this.position = new Vector2();
        this.orientation = 0;
    }

    @Override
    public Vector2 getPosition() {
        return position;
    }

    @Override
    public float getOrientation() {
        return orientation;
    }

    @Override
    public void setOrientation(float orientation) {
        this.orientation = orientation;
    }

    @Override
    public Location<Vector2> newLocation() {
        return new EntityLocation();
    }

    @Override
    public float vectorToAngle(Vector2 vector) {
        return B2DSteeringUtils.vectorToAngle(vector);
    }

    @Override
    public Vector2 angleToVector(Vector2 outVector, float angle) {
        return B2DSteeringUtils.angleToVector(outVector, angle);
    }
}
