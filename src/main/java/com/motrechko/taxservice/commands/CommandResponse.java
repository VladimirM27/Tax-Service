package com.motrechko.taxservice.commands;
import com.motrechko.taxservice.enums.Target;

/**

 This class represents the response of a Command execution in a front controller pattern.
 It contains the target of the response and the path to redirect the user to.
 */
public record CommandResponse(Target target, String path) {
}