package com.jme3.input.vr;

import com.jme3.app.VREnvironment;
import com.jme3.math.Matrix4f;
import com.jme3.math.Quaternion;
import com.jme3.math.Vector3f;

public class OpenVRTrackedController implements VRTrackedController{

	/**
	 * The index of the controller within the unserlying VR API.
	 */
	private int controllerIndex = -1;
	
	/**
	 * The underlying VRAPI.
	 */
	private OpenVRInput hardware     = null;
	
	/**
	 * The name of the controller.
	 */
	private String name;
	
	private VREnvironment environment;
	
    /**
	 * Wrap a new VR tracked controller on an OpenVR system.
	 * @param controllerIndex the index of the controller within the underlying VR system.
	 * @param hardware the underlying VR system.
	 * @param name the name of the controller.
	 * @param manufacturer the manufacturer of the controller.
	 * @param environment the VR environment.
	 */
    public OpenVRTrackedController(int controllerIndex, OpenVRInput hardware, String name, String manufacturer, VREnvironment environment){
    	this.controllerIndex = controllerIndex;
    	this.hardware        = hardware;
    	
    	this.name            = name;
    	this.manufacturer    = manufacturer;
    	
    	this.environment     = environment;
    }
	
	/**
	 * The manufacturer of the controller.
	 */
	private String manufacturer;
	
	@Override
	public Vector3f getPosition() {
		if (hardware != null){
			return hardware.getPosition(controllerIndex);
		} else {
			throw new IllegalStateException("No underlying VR API.");
		}
	}

	@Override
	public Quaternion getOrientation() {
        if (hardware != null){
			return hardware.getOrientation(controllerIndex);
		} else {
			throw new IllegalStateException("No underlying VR API.");
		}
	}

	@Override
	public Matrix4f getPose(){
		
		if (environment != null){
			 if (hardware != null){
					return ((OpenVR)environment.getVRHardware()).poseMatrices[controllerIndex];
				} else {
					throw new IllegalStateException("No underlying VR API.");
				}
		} else {
			throw new IllegalStateException("VR tracked device is not attached to any environment.");
		}
		
		
	}
	
	@Override
	public String getControllerName() {
		return name;
	}

	@Override
	public String getControllerManufacturer() {
		return manufacturer;
	}
}
